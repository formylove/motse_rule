package com.thizgroup.promotion.response;

import com.thizgroup.promotion.bean.dto.RecordDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Data
public class RecordsResponse {
  // 返回匹配的记录列表
  private List<RecordDTO> records = new ArrayList<>();
}
