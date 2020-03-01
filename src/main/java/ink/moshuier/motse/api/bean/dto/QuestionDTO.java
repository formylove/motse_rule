package ink.moshuier.motse.api.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Sarah Xu
 * @date : 2019-07-19
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO extends BaseDTO {
    private String question;
    private Integer proportion;
}