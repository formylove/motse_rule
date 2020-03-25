package ink.moshuier.motse.api.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author : Sarah Xu
 * @date : 2019-07-19
 **/
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO extends BaseDTO {
    private String question;
    private Integer proportion;
}