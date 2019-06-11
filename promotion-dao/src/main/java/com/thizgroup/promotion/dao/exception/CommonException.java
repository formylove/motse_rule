package com.thizgroup.promotion.dao.exception;

import lombok.Data;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Data
public class CommonException extends RuntimeException {
  private Integer code;
  private String errMsg;

  public CommonException(Integer code, String errMsg) {
    this.code = code;
    this.errMsg = errMsg;
  }

  public CommonException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errMsg = errorCode.getMessage();
    this.code = errorCode.getCode();
  }

  @Override
  public String getMessage() {
    return errMsg;
  }
}
