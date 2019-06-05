package com.thizgroup.promotion.api.impl;

import com.thizgroup.promotion.api.PromotionApi;
import com.thizgroup.promotion.dao.RecordDao;
import com.thizgroup.promotion.dao.bean.RecordBean;
import com.thizgroup.promotion.dao.bean.ResponseBean;
import com.thizgroup.promotion.model.util.ConstantUtils;
import com.thizgroup.promotion.service.service.ExportExcelService;
import com.thizgroup.promotion.service.service.VericationCodeService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Controller
public class PromotionApiImpl implements PromotionApi {
  private final RecordDao recordDao;

  private final ExportExcelService exportExcelService;

  private final VericationCodeService vericationCodeService;

  @Autowired
  public PromotionApiImpl(
      RecordDao recordDao,
      ExportExcelService exportExcelService,
      VericationCodeService vericationCodeService) {
    this.recordDao = recordDao;
    this.exportExcelService = exportExcelService;
    this.vericationCodeService = vericationCodeService;
  }

  @Override
  public ResponseBean<Void> addRecord(PromotionApi.RecordAddingRequest addingRequest) {

    vericationCodeService.checkVerifyCode(
        addingRequest.getVerificationCode(),
        addingRequest.getSmsToken(),
        addingRequest.getMobile());
    recordDao.addRecord(addingRequest.getMobile(), addingRequest.getInviteCode());

    return new ResponseBean<>();
  }

  @Override
  public ResponseBean<List<RecordDTO>> getRecords(RecordRetrieveRequest req) {
    List<RecordDTO> recordDTOS = new ArrayList<>();
    List<RecordBean> recordBeans =
        recordDao.getRecords(req.getInviteCodes(), req.getBeginTime(), req.getEndTime());
    for (RecordBean recordBean : recordBeans) {
      recordDTOS.add(new RecordDTO(recordBean));
    }
    return new ResponseBean(recordDTOS);
  }

  @Override
  public ResponseBean<SendVerificationCodeResponseBean> getVerificationCode(String mobile) {
    String vericationCode = vericationCodeService.sendVericationCode(mobile);
    Date expireDate = new Date();
    // 有效时间5分钟；验证码+固定字符作为加密密钥
    expireDate.setTime(System.currentTimeMillis() + ConstantUtils.SMS_VALID_TIME);
    System.out.println(expireDate);
    Map claims = new HashMap<>();
    claims.put(ConstantUtils.CLAIM_KEY_MOBILE, mobile);
    String smsToken =
        Jwts.builder()
            .setClaims(claims)
            .setExpiration(expireDate) // 必须在setClaims之前，否则不生效
            .signWith(ConstantUtils.getSecretKey(vericationCode))
            .compact();

    return new ResponseBean<SendVerificationCodeResponseBean>(
        new SendVerificationCodeResponseBean(smsToken));
  }

  @Override
  public void getExcel(HttpServletResponse response) {

    try {
      exportExcelService.exportExcel(response.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
    response.setHeader("content-type", "application/octet-stream");
    response.setContentType("application/octet-stream");
    // 下载文件能正常显示中文
    try {
      response.setHeader(
          "Content-Disposition",
          "attachment;filename="
              + URLEncoder.encode(ConstantUtils.generateExcelFileName(), "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }
}
