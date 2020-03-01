package ink.moshuier.motse.api.bean.request;

import ink.moshuier.motse.api.bean.dto.QuestionDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author : Sarah Xu
 * @date : 2020-02-26
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnairRequest implements BaseRequest {
    @ApiModelProperty("taskId")
    @NotNull
    private Long taskId;
    @ApiModelProperty("questions")
    @NotEmpty
    private List<QuestionDTO> questions;
}
