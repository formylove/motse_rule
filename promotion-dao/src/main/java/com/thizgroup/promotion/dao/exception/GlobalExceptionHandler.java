package com.thizgroup.promotion.dao.exception;

import com.thizgroup.promotion.dao.bean.ResponseBean;
import com.thizgroup.promotion.model.util.ConstantUtils;
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
public class GlobalExceptionHandler {

  @Autowired private MessageSource messageSource;

  @InitBinder
  public void initBinder(WebDataBinder binder) {}

  @ExceptionHandler(value = CommonException.class)
  @ResponseBody
  public ResponseBean handleAppException(CommonException e) {
    String message = I18nMessage(e.getMessage());
    return new ResponseBean(e.getCode(), message);
  }
  //  @ExceptionHandler(BindException.class)
  //  public ResponseBean handleException(MethodArgumentNotValidException e) {
  //    return new ResponseBean(1001, e.getBindingResult());
  //  }

  @ExceptionHandler(Exception.class)
  public ResponseBean handleException(Exception e) {
    if (ConstantUtils.MISSING_JWT.equals(e.getMessage())) {
      return new ResponseBean(
          ErrorCode.MISSING_JWT.getCode(), I18nMessage(ErrorCode.MISSING_JWT.getMessage()));
    }
    System.out.println("########################## " + e + " ##########################");
    return new ResponseBean(1000, e.getMessage());
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
    }
    return localeMessage;
  }
}
