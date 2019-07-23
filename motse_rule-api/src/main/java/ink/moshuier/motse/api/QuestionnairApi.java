package ink.moshuier.motse.api;

import ink.moshuier.motse.api.bean.ResponseBean;
import ink.moshuier.motse.api.bean.dto.QuestionDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Api(value = "问卷")
@RestController
@RequestMapping("/questionnair")
public interface QuestionnairApi extends RestfulApi {


  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
   class QuestionnairRequest {
    @ApiModelProperty("taskId")
    @NotNull
    private Long taskId;
    @ApiModelProperty("questions")
    @NotEmpty
    private List<QuestionDTO> questions;
  }


  @ApiOperation(value = "添加问卷")
  @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
   ResponseBean<Long> addQuestionnair(@Validated @RequestBody QuestionnairRequest questionnairRequest);


}
