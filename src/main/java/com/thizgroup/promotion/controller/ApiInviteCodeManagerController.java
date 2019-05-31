package com.thizgroup.promotion.controller;

import com.thizgroup.promotion.bean.request.RecordAddingRequest;
import com.thizgroup.promotion.bean.request.RecordRetrieveRequest;
import com.thizgroup.promotion.response.RecordsResponse;
import com.thizgroup.promotion.response.StatusResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@RestController
@RequestMapping("/binding/manager/")
public interface ApiInviteCodeManagerController {
  @PostMapping(value = "/record", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public StatusResponse addRecord(@RequestBody RecordAddingRequest addingRequest);

  @GetMapping(value = "/records/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public RecordsResponse getRecords(RecordRetrieveRequest req);

  @GetMapping(
      value = "/mobile/{mobile}/verification/code",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public SendVerificationCodeResponseBean getVerificationCode(
      @PathVariable("mobile") String mobile);

  @GetMapping(value = "/excel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String getExcel(HttpServletResponse response);

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  class SendVerificationCodeResponseBean {
    String smsToken;
  }
}
