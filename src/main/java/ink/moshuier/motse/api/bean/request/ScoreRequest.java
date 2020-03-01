package ink.moshuier.motse.api.bean.request;

import ink.moshuier.motse.api.bean.dto.QuestionDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : Sarah Xu
 * @date : 2020-02-26
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScoreRequest implements BaseRequest {
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
    private QuestionDTO questionnair;
}