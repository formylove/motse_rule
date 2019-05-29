package com.thizgroup.coderecorder.controller.impl;

import com.thizgroup.coderecorder.bean.bo.RecordBean;
import com.thizgroup.coderecorder.bean.dto.RecordDTO;
import com.thizgroup.coderecorder.bean.request.RecordAddingRequest;
import com.thizgroup.coderecorder.bean.request.RecordRetrieveRequest;
import com.thizgroup.coderecorder.controller.ApiInviteCodeManagerController;
import com.thizgroup.coderecorder.dao.RecordDao;
import com.thizgroup.coderecorder.response.RecordsResponse;
import com.thizgroup.coderecorder.response.StatusResponse;
import com.thizgroup.coderecorder.service.VericationCodeService;
import com.thizgroup.coderecorder.util.ConstantUtils;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 **/
@Controller
public class InviteCodeManagerControllerImpl implements ApiInviteCodeManagerController {
@Autowired
    RecordDao recordDao;
@Autowired
    VericationCodeService vericationCodeService;
    public StatusResponse addRecord(RecordAddingRequest addingRequest){

        vericationCodeService.checkVerifyCode(addingRequest.getVerificationCode(), addingRequest.getSmsToken(), addingRequest.getMobile());
        recordDao.addRecord(addingRequest.getMobile(),addingRequest.getInviteCode());

        return new StatusResponse();
    }
    public RecordsResponse getRecords(RecordRetrieveRequest req){
        List<RecordBean> recordBeans = recordDao.getRecords(req.getInviteCodes(),req.getBeginTime(),req.getEndTime());
        RecordsResponse recordsResponse = new RecordsResponse();
        for(RecordBean recordBean:recordBeans){
            recordsResponse.getRecords().add(new RecordDTO(recordBean));
        }
        return recordsResponse;
    }

    @Override
    public SendVerificationCodeResponseBean getVerificationCode(String mobile) {
        String vericationCode = vericationCodeService.sendVericationCode(mobile);
        Date expireDate = new Date();
        // 有效时间5分钟；验证码+固定字符作为加密密钥
        expireDate.setTime(System.currentTimeMillis() + ConstantUtils.SMS_VALID_TIME);
        Map claims = new HashMap<>();
        claims.put(ConstantUtils.CLAIM_KEY_MOBILE,mobile);
        String smsToken =
                Jwts.builder()
                        .setClaims(claims)
                        .setExpiration(expireDate)
                        .signWith(ConstantUtils.getSecretKey(vericationCode))
                        .compact();

        return new SendVerificationCodeResponseBean(smsToken);
    }
}
