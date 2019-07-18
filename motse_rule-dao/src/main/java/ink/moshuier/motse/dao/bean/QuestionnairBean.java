package ink.moshuier.motse.dao.bean;

import ink.moshuier.motse.model.entity.QuestionnairEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Sarah Xu
 * @date : 2019-07-12
 **/
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class QuestionnairBean {
    private Long id;
    private List<QuestionBean> questions;
    private TaskBean taskBean;

    public QuestionnairBean(QuestionnairEntity questionnairEntity) {
        this.id = questionnairEntity.getId();
        this.taskBean = new TaskBean(questionnairEntity.getTaskEntity());
        this.questions = questionnairEntity.getQuestions().stream().map((questionEntity)->new QuestionBean(questionEntity)).collect(Collectors.toList());
    }

}
