package com.thizgroup.coderecorder.service;

import com.thizgroup.coderecorder.bean.sms.SmsSendRequest;
import com.thizgroup.coderecorder.bean.sms.SmsSendResponse;
import com.thizgroup.coderecorder.exception.CommonException;
import com.thizgroup.coderecorder.exception.ErrorCode;
import com.thizgroup.coderecorder.exception.JsonException;
import com.thizgroup.coderecorder.util.*;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.Date;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 **/
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

    public Boolean isTest() {
        // String profiles = System.getProperty(SPRING_PROFILES_ACTIVE);
        return profileActive.equals("test");
    }

    public String sendVericationCode(String mobile){
        Boolean isMobile = ValidationRuleUtil.isMobile(mobile);
        if (!isMobile) {
            throw new CommonException(
                    ErrorCode.WRONG_MOBILE_NUM);
        }

        String verifyCode = isTest()?ConstantUtils.TEST_VERIFY_CODE:VericationCodeGenerator.generate();
        String message = MessageFormat.format(template,verifyCode);
        String report = "true";
        SmsSendRequest smsSingleRequest =
                new SmsSendRequest(account, password, message, mobile, report);
        String requestJson = JsonUtils.toJson(smsSingleRequest);
        String response = SmsSendUtil.sendSmsByPost(smsUrl, requestJson);
        SmsSendResponse smsSendResponse = null;
        try {
            smsSendResponse = JsonUtils.jsonToObject(response, SmsSendResponse.class);
        } catch (JsonException e) {
            e.printStackTrace();
        }
        if (!smsSendResponse.getCode().equals("0")) {
                throw new CommonException(
                        Integer.parseInt(smsSendResponse.getCode()), smsSendResponse.getErrorMsg());
            }
        return verifyCode;
    }


public void checkVerifyCode(String verifyCode,String smsToken,String acquiredMobile){
    try {
        String mobile =
                (String) Jwts.parser()
                        .setSigningKey(ConstantUtils.getSecretKey(verifyCode))
                        .parseClaimsJws(smsToken)
                        .getBody().get(ConstantUtils.CLAIM_KEY_MOBILE);

        if (mobile != null) {
            //防止利用正确的手机验证码绑定另一部手机，必须发送短信的手机和绑定的手机是同一部
            if (!mobile.equals(acquiredMobile)) {
                throw new CommonException(ErrorCode.NOT_PREVIOUS_MOBILE);
        }
        }
    } catch (ExpiredJwtException e) {
        throw new CommonException(
                ErrorCode.VERIFICATION_CODE_EXPIRED);
    } catch (SignatureException e) {
        throw new CommonException(
                ErrorCode.VERIFICATION_CODE_UNMATCHED);
    }
}
}
