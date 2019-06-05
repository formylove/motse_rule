package com.thizgroup.promotion.model.entity;

import com.thizgroup.promotion.model.converter.BooleanStringConverter;
import com.thizgroup.promotion.model.converter.InstantConverter;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

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
  @GeneratedValue(generator = "idGenerator")
  @GenericGenerator(
      name = "idGenerator",
      strategy = "com.thizgroup.promotion.model.util.UUIDGenerator")
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
