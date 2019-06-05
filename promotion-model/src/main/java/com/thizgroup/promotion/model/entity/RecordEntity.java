package com.thizgroup.promotion.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Data
@Entity
@Table(name = "jfa_promotion_record")
@NoArgsConstructor
public class RecordEntity extends BaseEntity {

  // 手机号
  @Column(name = "mobile", nullable = false, columnDefinition = "VARCHAR(20)")
  private String mobile;
  // 邀请码
  @Column(name = "invite_code", nullable = false, columnDefinition = "VARCHAR(8)")
  private String inviteCode;

  @Builder
  public RecordEntity(String mobile, String inviteCode) {
    this.mobile = mobile;
    this.inviteCode = inviteCode;
  }
}
