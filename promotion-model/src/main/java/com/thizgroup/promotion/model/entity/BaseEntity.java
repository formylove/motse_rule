package com.thizgroup.promotion.model.entity;

import com.thizgroup.promotion.model.converter.BooleanStringConverter;
import com.thizgroup.promotion.model.converter.InstantConverter;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

  /* Primary key */
  @Id
  @Column(
      name = "id",
      unique = true,
      nullable = false,
      updatable = false,
      length = 64,
      columnDefinition = "CHAR(64)")
/*  @GeneratedValue(generator = "idGenerator")
  @GenericGenerator(
      name = "idGenerator",
      strategy = "com.thizgroup.promotion.model.util.UUIDGenerator")
      主键生成方式放到dao层，弱化对DB的依赖*/
  protected String id;

  /* Soft delete flag */
  @Column(name = "active_status", nullable = false)
  @Convert(converter = BooleanStringConverter.class)
  protected boolean activeStatus = true;

  @CreatedBy
  @Column(name = "created_by", nullable = false, length = 64)
  protected String createdBy = "";

  @CreatedDate
  @Column(name = "creation_date", nullable = false)
  @Convert(converter = InstantConverter.class)
  protected Instant creationDate;

  @LastModifiedBy
  @Column(name = "last_updated_by", nullable = false, length = 64)
  protected String lastUpdatedBy = "";

  @LastModifiedDate
  @Column(name = "last_update_date", nullable = false)
  @Convert(converter = InstantConverter.class)
  protected Instant lastUpdateDate;
}
