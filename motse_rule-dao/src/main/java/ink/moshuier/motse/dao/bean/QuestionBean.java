package ink.moshuier.motse.dao.bean;

import ink.moshuier.motse.model.entity.QuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author : Sarah Xu
 * @date : 2019-07-12
 **/
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionBean extends BaseBean {
    private String question;
    private int proportion;

    public QuestionBean(QuestionEntity questionEntity) {
        super.id = questionEntity.getId();
        this.question = questionEntity.getQuestion();
        this.proportion = questionEntity.getProportion();
    }
}
