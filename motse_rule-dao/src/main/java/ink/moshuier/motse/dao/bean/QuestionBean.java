package ink.moshuier.motse.dao.bean;

import ink.moshuier.motse.model.entity.QuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author : Sarah Xu
 * @date : 2019-07-12
 **/
@Builder
@AllArgsConstructor
@Data
public class QuestionBean {
    private Long id;
    private String question;
    private int proportion;

    public QuestionBean(QuestionEntity questionEntity) {
        this.id = questionEntity.getId();
        this.question=questionEntity.getQuestion();
        this.proportion=questionEntity.getProportion();
    }
}
