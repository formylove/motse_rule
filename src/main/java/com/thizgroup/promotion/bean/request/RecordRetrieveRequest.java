package com.thizgroup.promotion.bean.request;

import lombok.Data;

import java.util.List;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Data
public class RecordRetrieveRequest {

  // 邀请码list
  List<String> inviteCodes;
  // 起始时间
  private Long beginTime;
  // 结束时间
  private Long endTime;
}
