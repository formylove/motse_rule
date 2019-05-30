package com.thizgroup.promotion.controller.impl;

import com.thizgroup.promotion.bean.bo.RecordBean;
import com.thizgroup.promotion.bean.dto.RecordDTO;
import com.thizgroup.promotion.bean.request.RecordAddingRequest;
import com.thizgroup.promotion.bean.request.RecordRetrieveRequest;
import com.thizgroup.promotion.controller.ApiInviteCodeManagerController;
import com.thizgroup.promotion.dao.RecordDao;
import com.thizgroup.promotion.response.RecordsResponse;
import com.thizgroup.promotion.response.StatusResponse;
import com.thizgroup.promotion.service.ExportExcelService;
import com.thizgroup.promotion.service.VericationCodeService;
import com.thizgroup.promotion.util.ConstantUtils;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
    ExportExcelService exportExcelService;
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
    System.out.println(expireDate);
        Map claims = new HashMap<>();
        claims.put(ConstantUtils.CLAIM_KEY_MOBILE,mobile);
        String smsToken =
                Jwts.builder()
                        .setClaims(claims)
                        .setExpiration(expireDate)//必须在setClaims之前，否则不生效
                        .signWith(ConstantUtils.getSecretKey(vericationCode))
                        .compact();

        return new SendVerificationCodeResponseBean(smsToken);
    }

    @Override
    public String getExcel(HttpServletResponse response) {

        try {
            exportExcelService.exportExcel(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        // 下载文件能正常显示中文
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(ConstantUtils.generateExcelFileName(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
