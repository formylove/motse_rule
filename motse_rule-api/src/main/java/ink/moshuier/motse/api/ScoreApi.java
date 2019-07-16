package ink.moshuier.motse.api;

import ink.moshuier.motse.api.bean.ResponseBean;
import ink.moshuier.motse.dao.bean.QuestionnairBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Api(value = "习惯/长期任务")
@RestController
@RequestMapping("/habit/")
public interface ScoreApi extends RestfulApi {
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public class ScoreRequest {
    @ApiModelProperty("任务id")
    @NotBlank
    private Long id;

    @ApiModelProperty("日期")
    @NotBlank
    private Long date;

    @ApiModelProperty("分数")
    @NotBlank
    private Integer score;

    @ApiModelProperty("习惯/长期任务")
    @NotNull
    private QuestionnairBean questionnair;
  }

  @ApiOperation(value = "更改分数")
  @PostMapping(value = "/score/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseBean<Void> rate(@RequestBody ScoreRequest request);





}
