package com.thizgroup.promotion.exception;

import com.thizgroup.promotion.response.StatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Created by jf on 2019/4/29. */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @Autowired private MessageSource messageSource;

  @InitBinder
  public void initBinder(WebDataBinder binder) {}

  @ExceptionHandler(value = CommonException.class)
  @ResponseBody
  public StatusResponse handleAppException(CommonException e) {
    String message = I18nMessage(e.getMessage());
    return new StatusResponse(e.getCode(), message);
  }

  @ExceptionHandler(Exception.class)
  public StatusResponse handleException(Exception e) {
    log.error("handleException :", e);
    return new StatusResponse(1000, e.getMessage());
  }

  /**
   * 国际化接口输出
   *
   * @param messageCode
   * @return
   */
  private String I18nMessage(String messageCode) {
    String localeMessage = null;
    try {
      localeMessage = messageSource.getMessage(messageCode, null, LocaleContextHolder.getLocale());
    } catch (NoSuchMessageException e1) {
      e1.printStackTrace();
      log.error("no such i18n config:", e1);
    }
    return localeMessage;
  }
}
