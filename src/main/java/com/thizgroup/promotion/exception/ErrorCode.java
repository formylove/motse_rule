package com.thizgroup.promotion.exception;

/** Created by jf on 2019/4/24. */
public enum ErrorCode {
  WRONG_MOBILE_NUM(1000, "wrong.mobile.num"),
  VERIFICATION_CODE_EXPIRED(2000, "verification.code.expired"),
  VERIFICATION_CODE_UNMATCHED(3000, "verification.code.unmatched"),
  NOT_PREVIOUS_MOBILE(4000, "not.previous.mobile"),
  MOBILE_HAS_BINDED(5000, "mobile.has.binded"),
  MOBILE_AND_CODE_HAS_BINDED(6000, "mobile.verification.code.has.binded")
  ;
  private final int code;
  private final String message;

  private ErrorCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  /**
   * 获取错误信息对应国际化配置
   *
   * @param message
   * @return
   */
  public static String getI18nMessage(String message) {
    ErrorCode[] values = ErrorCode.values();
    for (ErrorCode value : values) {
      if (value.getMessage().equals(message)) {
        return value.getMessage();
      }
    }
    return message;
  }
}
