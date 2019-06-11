package com.thizgroup.promotion.api.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;


@Data
@ResponseBody
public class ResponseBean<E> {
  Integer errCode = 0;
  @JsonRawValue String errMsg = "\"OK\"";

  @JsonInclude(JsonInclude.Include.NON_NULL)
  Integer pageNumber;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  Integer pageSize;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  Integer totalPages;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  Long totalCount;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  E data = null;

  public ResponseBean(E data) {
    this.data = data;
  }

  public ResponseBean(Integer errCode, String errMsg) {
    this.errCode = errCode;
    this.errMsg = errMsg;
  }

  public ResponseBean() {}
}
