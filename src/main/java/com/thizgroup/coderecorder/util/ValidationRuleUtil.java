package com.thizgroup.coderecorder.util;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Created by jf on 2019/5/22. */
@Slf4j
public class ValidationRuleUtil {

  public static final String REGEX_MOBILE = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
  public static final String REGEX_LOGIN_PASS = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
  public static final String REGEX_BANK_PASS = "^\\d{6}$";
  /**
  * @Description: 登陆密码规则验证
  * @Param: [password]
  * @return: java.lang.Boolean
  * @Author: wlf
  * @Date: 2019/5/22
  */
  public static Boolean isLoginPasswword(String password) {
    // 需6-16位字符，必须同时包含数字，字母
    Pattern pattern = Pattern.compile(REGEX_LOGIN_PASS);
    Matcher matcher = pattern.matcher(password);
    // 字符串是否与正则表达式相匹配
    return matcher.matches();
  }

  /**
  * @Description: 提现密码规则验证
  * @Param: [password]
  * @return: java.lang.Boolean
  * @Author: wlf
  * @Date: 2019/5/22
  */
  public static Boolean isBankPasswword(String password) {
    // 6个数字
    Pattern pattern = Pattern.compile(REGEX_BANK_PASS);
    Matcher matcher = pattern.matcher(password);
    // 字符串是否与正则表达式相匹配
    return matcher.matches();
  }

  /**
  * @Description: 手机号码验证
  * @Param: [password]
  * @return: java.lang.Boolean
  * @Author: wlf
  * @Date: 2019/5/22
  */
  public static Boolean isMobile(String mobile) {
    // 编译正则表达式
    Pattern pattern = Pattern.compile(REGEX_MOBILE);
    Matcher matcher = pattern.matcher(mobile);
    // 字符串是否与正则表达式相匹配
    return matcher.matches();
  }

//  public static void main(String[] args) {
//    log.info("====" + isBankPasswword("123"));
//    log.info("====" + isBankPasswword("123456"));
//    log.info("====" + isBankPasswword("qqqqqq"));
//    log.info("====" + isLoginPasswword("144fed"));
//    log.info("====" + isLoginPasswword("Qazxsw123"));
//    log.info("====" + isMobile("13111111111"));
//    log.info("====" + isMobile("15822222222"));
//  }
}
