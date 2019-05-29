package com.thizgroup.coderecorder.dao.repo;

import com.thizgroup.coderecorder.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 **/
@Repository
public interface RecordRepository   extends JpaRepository<RecordEntity, String>, JpaSpecificationExecutor<RecordEntity> {
    public Optional<RecordEntity> findAllByMobile(String mobile);
}
