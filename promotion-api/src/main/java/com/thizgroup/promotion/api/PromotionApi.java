package com.thizgroup.promotion.api;

import com.thizgroup.promotion.dao.bean.RecordBean;
import com.thizgroup.promotion.dao.bean.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Api(value = "手机号邀请码绑定")
@RestController
@RequestMapping("/binding/manager/")
public interface PromotionApi extends RestfulApi {
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public class RecordAddingRequest {
    // 手机号
    @ApiModelProperty("手机号")
    @Pattern(regexp = "\\d+", message = "{validation.error.invite.code")
    private String mobile;

    // 邀请码
    @Pattern(regexp = "[A-Z\\d]{6}", message = "{validation.error.invite.code}")
    @ApiModelProperty("邀请码")
    private String inviteCode;

    // 验证码
    @Length(min = 6, max = 6, message = "{validation.error.verification.code.length}")
    @ApiModelProperty("验证码")
    private String verificationCode;

    // token
    @ApiModelProperty("Token")
    @NotEmpty
    private String smsToken;
  }

  @ApiOperation(value = "注册")
  @PostMapping(value = "/record", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseBean<Void> addRecord(@Validated @RequestBody RecordAddingRequest addingRequest);

  @Data
  public class RecordRetrieveRequest {

    // 邀请码list
    @ApiModelProperty("邀请码")
    List<String> inviteCodes;

    // 起始时间
    @ApiModelProperty("起始时间")
    private Long beginTime;

    // 结束时间
    @ApiModelProperty("结束时间")
    private Long endTime;
  }

  @Data
  public class RecordDTO {
    // 手机
    public String mobile;
    // 邀请码
    public String inviteCode;
    // 创建时间
    public Long createTime;

    public RecordDTO(RecordBean recordBean) {
      this.mobile = recordBean.getMobile();
      this.inviteCode = recordBean.getInviteCode();
      this.createTime = recordBean.getCreationDate();
    }
  }

  @ApiOperation("过滤获取所有绑定记录")
  @GetMapping(value = "/records/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseBean<List<RecordDTO>> getRecords(@Validated RecordRetrieveRequest req);

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  class SendVerificationCodeResponseBean {
    @ApiModelProperty(value = "短信验证token")
    String smsToken;
  }

  @ApiOperation(value = "获取验证码")
  @GetMapping(
      value = "/mobile/{mobile}/verification/code",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseBean<SendVerificationCodeResponseBean> getVerificationCode(
      @PathVariable("mobile") String mobile);

  @ApiOperation(value = "下载excel")
  @GetMapping(value = "/excel", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public void getExcel(HttpServletResponse response);
}
