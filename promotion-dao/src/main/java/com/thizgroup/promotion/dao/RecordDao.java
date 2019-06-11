package com.thizgroup.promotion.dao;

import com.thizgroup.promotion.dao.bean.RecordBean;
import com.thizgroup.promotion.dao.exception.CommonException;
import com.thizgroup.promotion.dao.exception.ErrorCode;
import com.thizgroup.promotion.dao.repo.RecordEntityRepository;
import com.thizgroup.promotion.model.entity.RecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Repository
@Transactional(readOnly = true)
public class RecordDao {
  private final RecordEntityRepository recordEntityRepository;

  @Autowired
  public RecordDao(RecordEntityRepository recordEntityRepository) {
    this.recordEntityRepository = recordEntityRepository;
  }

  /**
   * @Description: 添加绑定 @Param: [mobile, inviteCode]
   *
   * @return: void @Author: Sarah Xu @Date: 2019/6/5
   */
  @Transactional
  public void addRecord(String mobile, String inviteCode) {
    // 相同邀请码和手机号不能重复绑定
    if (recordEntityRepository.findAllByMobileAndAndInviteCode(mobile, inviteCode).isPresent()) {
      throw new CommonException(ErrorCode.MOBILE_AND_CODE_HAS_BINDED);
    }

    // 暂时为可重复绑定
    //    if (recordRepository.findAllByMobile(mobile).isPresent()) {
    //      throw new CommonException(ErrorCode.MOBILE_HAS_BINDED);
    //    }
      RecordEntity recordEntity = recordEntityRepository.newEntity();
      recordEntity.setInviteCode(inviteCode);
      recordEntity.setMobile(mobile);
     recordEntity =
        recordEntityRepository.saveAndFlush(recordEntity);
  }
  /**
   * @Description: 获取条件过滤后的绑定记录 @Param: [inviteCodes, beginTime, endTime]
   *
   * @return: java.util.List<com.thizgroup.promotion.dao.bean.RecordBean> @Author: Sarah Xu @Date:
   *     2019/6/5
   */
  public List<RecordBean> getRecords(List<String> inviteCodes, Long beginTime, Long endTime) {

    Specification<RecordEntity> specification =
        (Root<RecordEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) -> {
          List<Predicate> predicates = new ArrayList<>();
          Path path_invite_code = root.get("inviteCode");
          Path path_creation_time = root.get("creationDate");
          if (inviteCodes != null && inviteCodes.size() > 0) {
            predicates.add(cb.in(path_invite_code).value(inviteCodes));
          }
          if (beginTime != null) {
            predicates.add(cb.greaterThanOrEqualTo(path_creation_time, beginTime));
          }
          if (endTime != null) {
            predicates.add(cb.lessThanOrEqualTo(path_creation_time, endTime));
          }

          Predicate[] p = new Predicate[predicates.size()];
          criteriaQuery.where(cb.and(predicates.toArray(p)));
          criteriaQuery.orderBy(cb.desc(root.get("creationDate")));
          return predicates.isEmpty() ? cb.conjunction() : criteriaQuery.getRestriction();
        };

    List<RecordEntity> recordEntities = recordEntityRepository.findAll(specification);
    List<RecordBean> recordBeans = new ArrayList<>();
    for (RecordEntity recordEntity : recordEntities) {
      recordBeans.add(new RecordBean(recordEntity));
    }

    return recordBeans;
  }
  /**
   * @Description: 获取所有绑定记录 @Param: []
   *
   * @return: java.util.List<com.thizgroup.promotion.dao.bean.RecordBean> @Author: Sarah Xu @Date:
   *     2019/6/5
   */
  public List<RecordBean> getAllRecords() {
    List<RecordEntity> recordEntities = recordEntityRepository.findAll();
    List<RecordBean> recordBeans = new ArrayList<>();
    for (RecordEntity recordEntity : recordEntities) {
      recordBeans.add(new RecordBean(recordEntity));
    }
    return recordBeans;
  }
}
