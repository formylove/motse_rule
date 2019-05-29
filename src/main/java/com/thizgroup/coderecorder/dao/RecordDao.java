package com.thizgroup.coderecorder.dao;

import com.thizgroup.coderecorder.bean.bo.RecordBean;
import com.thizgroup.coderecorder.dao.repo.RecordRepository;
import com.thizgroup.coderecorder.entity.RecordEntity;
import com.thizgroup.coderecorder.exception.CommonException;
import com.thizgroup.coderecorder.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Repository
public class RecordDao {
  @Autowired RecordRepository recordRepository;

  public void addRecord(String mobile, String inviteCode) {
    if (recordRepository.findAllByMobile(mobile).isPresent()) {
      throw new CommonException(ErrorCode.MOBILE_HAS_BINDED);
    }
    recordRepository.saveAndFlush(
        RecordEntity.builder().inviteCode(inviteCode).mobile(mobile).build());
  }

  public List<RecordBean> getRecords(List<String> inviteCodes, Long beginTime, Long endTime) {

    Specification<RecordEntity> specification =
        (Root<RecordEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) -> {
          List<Predicate> predicates = new ArrayList<>();
          Path path_invite_code = root.get("inviteCode");
          Path path_creation_time = root.get("createTime");
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
          criteriaQuery.orderBy(cb.desc(root.get("createTime")));
          return predicates.isEmpty() ? cb.conjunction() : criteriaQuery.getRestriction();
        };

    List<RecordEntity> recordEntities = recordRepository.findAll(specification);
    List<RecordBean> recordBeans = new ArrayList<>();
    for (RecordEntity recordEntity : recordEntities) {
      recordBeans.add(new RecordBean(recordEntity));
    }

    return recordBeans;
  }

  public List<RecordBean> getAllRecords() {
    List<RecordEntity> recordEntities = recordRepository.findAll();
    List<RecordBean> recordBeans = new ArrayList<>();
    for (RecordEntity recordEntity : recordEntities) {
      recordBeans.add(new RecordBean(recordEntity));
    }
    return recordBeans;
  }
}
