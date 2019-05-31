package com.thizgroup.promotion.bean.bo;

import com.thizgroup.promotion.entity.RecordEntity;
import lombok.Data;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Data
public class RecordBean {
  private Integer id;
  // 手机号
  private String mobile;
  // 邀请码
  private String inviteCode;
  // 创建时间
  private Long createTime;

  public RecordBean(RecordEntity recordEntity) {
    this.id = recordEntity.getId();
    this.mobile = recordEntity.getMobile();
    this.inviteCode = recordEntity.getInviteCode();
    this.createTime = recordEntity.getCreateTime();
  }
}
