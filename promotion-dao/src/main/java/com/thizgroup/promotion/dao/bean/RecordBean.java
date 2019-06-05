package com.thizgroup.promotion.dao.bean;

import com.thizgroup.promotion.model.entity.RecordEntity;
import lombok.Data;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Data
public class RecordBean {
  private String id;
  // 手机号
  private String mobile;
  // 邀请码
  private String inviteCode;
  // 创建时间
  private Long creationDate;

  public RecordBean(RecordEntity recordEntity) {
    this.id = recordEntity.getId();
    this.mobile = recordEntity.getMobile();
    this.inviteCode = recordEntity.getInviteCode();
    this.creationDate = recordEntity.getCreationDate().toEpochMilli();
  }
}
