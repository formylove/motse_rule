package com.thizgroup.promotion.dao.exception;

/** Created by jf on 2019/4/24. */
public enum ErrorCode {
  INVALID_MOBILE_NUM(108, "mobile.not.valid"),
  WRONG_MOBILE_NUM(1000, "wrong.mobile.num"),
  VERIFICATION_CODE_EXPIRED(2000, "verification.code.expired"),
  VERIFICATION_CODE_UNMATCHED(3000, "verification.code.unmatched"),
  NOT_PREVIOUS_MOBILE(4000, "not.previous.mobile"),
  MOBILE_HAS_BINDED(5000, "mobile.has.binded"),
  MOBILE_AND_CODE_HAS_BINDED(6000, "mobile.verification.code.has.binded"),
  MISSING_JWT(7000, "missing.jwt");
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

  public static String getMessageByCode(int code) {
    for (ErrorCode errorCode : ErrorCode.values()) {
      if (errorCode.getCode() == code) {
        return errorCode.getMessage();
      }
    }
    return null;
  }
}
