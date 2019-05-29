package com.thizgroup.coderecorder.bean.request;

import lombok.Data;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 **/
@Data
public class RecordAddingRequest {
    //手机号
    private String mobile;
    //邀请码
    private String inviteCode;
    //验证码
    private String verificationCode;
    //token
    private String smsToken;
}
