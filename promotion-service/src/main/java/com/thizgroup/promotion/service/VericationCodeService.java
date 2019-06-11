package com.thizgroup.promotion.service;

import com.thizgroup.promotion.dao.exception.CommonException;
import com.thizgroup.promotion.dao.exception.ErrorCode;
import com.thizgroup.promotion.dao.exception.JsonException;
import com.thizgroup.promotion.dao.util.ConstantUtils;
import com.thizgroup.promotion.service.sms.SmsSendRequest;
import com.thizgroup.promotion.service.sms.SmsSendResponse;
import com.thizgroup.promotion.service.util.JsonUtils;
import com.thizgroup.promotion.service.util.SmsSendUtil;
import com.thizgroup.promotion.service.util.ValidationRuleUtil;
import com.thizgroup.promotion.service.util.VericationCodeGenerator;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Service
public class VericationCodeService {
  @Value("${spring.profiles.active}")
  private String profileActive;

  @Value("${sms.account}")
  private String account;

  @Value("${sms.password}")
  private String password;

  @Value("${sms.url}")
  private String smsUrl;

  @Value("${sms.template}")
  String template;

  public Boolean isProd() {
    // String profiles = System.getProperty(SPRING_PROFILES_ACTIVE);
    return profileActive.equals("prod");
  }
  /**
   * @Description: 发送验证码 @Param: [mobile]
   *
   * @return: java.lang.String @Author: Sarah Xu @Date: 2019/6/5
   */
  public String sendVericationCode(String mobile) {
    Boolean isMobile = ValidationRuleUtil.isMobile(mobile);
    //        if (!isMobile) {
    //            throw new CommonException(
    //                    ErrorCode.WRONG_MOBILE_NUM);
    //        }

    String verifyCode =
        isProd() ? VericationCodeGenerator.generate() : ConstantUtils.TEST_VERIFY_CODE;
    String message = MessageFormat.format(template, verifyCode);
    String report = "true";
    if (isProd()) {
      SmsSendRequest smsSingleRequest =
          new SmsSendRequest(account, password, message, mobile, report);
      String requestJson = JsonUtils.toJson(smsSingleRequest);
      System.out.println(requestJson);
      String response = SmsSendUtil.sendSmsByPost(smsUrl, requestJson);
      SmsSendResponse smsSendResponse = null;
      try {
        smsSendResponse = JsonUtils.jsonToObject(response, SmsSendResponse.class);
      } catch (JsonException e) {
        e.printStackTrace();
      }
      if (!smsSendResponse.getCode().equals("0")) {
        throw new CommonException(
            Integer.parseInt(smsSendResponse.getCode()),
            ErrorCode.getMessageByCode(Integer.valueOf(smsSendResponse.getCode())));
      }
    }
    return verifyCode;
  }
  /**
   * @Description: 根据token和验证码验证验证码正确性 @Param: [verifyCode, smsToken, acquiredMobile]
   *
   * @return: void @Author: Sarah Xu @Date: 2019/6/5
   */
  public void checkVerifyCode(String verifyCode, String smsToken, String acquiredMobile) {
    try {
      String mobile =
          (String)
              Jwts.parser()
                  .setSigningKey(ConstantUtils.getSecretKey(verifyCode))
                  .parseClaimsJws(smsToken)
                  .getBody()
                  .get(ConstantUtils.CLAIM_KEY_MOBILE);

      if (mobile != null) {
        // 防止利用正确的手机验证码绑定另一部手机，必须发送短信的手机和绑定的手机是同一部
        if (!mobile.equals(acquiredMobile)) {
          throw new CommonException(ErrorCode.NOT_PREVIOUS_MOBILE);
        }
      }
    } catch (ExpiredJwtException e) {
      throw new CommonException(ErrorCode.VERIFICATION_CODE_EXPIRED);
    } catch (SignatureException e) {
      throw new CommonException(ErrorCode.VERIFICATION_CODE_UNMATCHED);
    }
  }
}
