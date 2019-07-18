package ink.moshuier.motse.api;

import ink.moshuier.motse.api.bean.PageResponseBean;
import ink.moshuier.motse.api.bean.ResponseBean;
import ink.moshuier.motse.dao.bean.QuestionBean;
import ink.moshuier.motse.dao.bean.QuestionnairBean;
import ink.moshuier.motse.dao.bean.TaskBean;
import ink.moshuier.motse.model.enums.Quarants;
import ink.moshuier.motse.model.enums.TaskType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Api(value = "问卷")
@RestController
@RequestMapping("/")
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

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public class QuestionDTO {
    private Long id;
    private String question;
    private int proportion;
  }


  @ApiOperation(value = "添加问卷")
  @PostMapping(value = "/tasks/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
   ResponseBean<Long> addQuestionnair(@Validated @RequestBody QuestionnairRequest questionnairRequest);



}
