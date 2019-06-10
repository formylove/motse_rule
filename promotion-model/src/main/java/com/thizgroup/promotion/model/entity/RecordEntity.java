package com.thizgroup.promotion.model.entity;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 * 实体需要序列化，实体只做数据库映射用，不参与业务
 */
@Data
@Entity
@Table(name = "jfa_promotion_record")
@NoArgsConstructor
public class RecordEntity extends BaseEntity implements Serializable {

  // 手机号
  @Column(name = "mobile", nullable = false, columnDefinition = "VARCHAR(20)")
  private String mobile;
  // 邀请码
  @Column(name = "invite_code", nullable = false, columnDefinition = "VARCHAR(8)")
  private String inviteCode;

/*  @Builder
  public RecordEntity(String mobile, String inviteCode) {
    this.mobile = mobile;
    this.inviteCode = inviteCode;
  }*/
}
