package ink.moshuier.motse.api.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : Sarah Xu
 * @date : 2019-07-19
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionnairDTO extends BaseDTO {
    Long taskId;
    private List<QuestionDTO> questions;
}