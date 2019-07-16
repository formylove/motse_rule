package ink.moshuier.motse.api.exception;


import ink.moshuier.motse.api.bean.ResponseBean;
import ink.moshuier.motse.dao.exception.CommonException;
import ink.moshuier.motse.dao.exception.ErrorCode;
import ink.moshuier.motse.dao.util.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseBean handleException(MethodArgumentNotValidException e) {
      BindingResult result = e.getBindingResult();
        FieldError fieldError = result.getFieldErrors().get(0);
        return new ResponseBean(1001,I18nMessage(fieldError.getDefaultMessage()));
    }


  @ExceptionHandler(Exception.class)
  public ResponseBean handleException(Exception e) {
//    if (ConstantUtils.MISSING_JWT.equals(e.getMessage())) {
//      return new ResponseBean(
//          ErrorCode.MISSING_JWT.getCode(), I18nMessage(ErrorCode.MISSING_JWT.getMessage()));
//    }
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
