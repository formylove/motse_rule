package com.thizgroup.promotion.dao.repo;

import com.thizgroup.promotion.model.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Repository
public interface RecordEntityRepository
    extends JpaRepository<RecordEntity, String>, JpaSpecificationExecutor<RecordEntity> {
  public Optional<RecordEntity> findAllByMobile(String mobile);

  public Optional<RecordEntity> findAllByMobileAndAndInviteCode(String mobile, String inviteCode);
}
