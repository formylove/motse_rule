package ink.moshuier.motse.dao.bean;

import ink.moshuier.motse.model.entity.TaskEntity;
import ink.moshuier.motse.model.enums.Quarants;
import ink.moshuier.motse.model.enums.TaskType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : Sarah Xu
 * @date : 2019-07-12
 **/
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskBean extends BaseBean {
    private Long id;
    private String title;
    private TaskType type;
    //执行频率
    private int frequency;
    //价值
    private Long value;
    private Quarants quarant;


    //固定时间段
    private Integer from;
    private int tomatoes;

    private Long score;
    private QuestionnairBean questionnairBean;
    //任务开始时间
    private Long startDate;
    private Long endDate;
    private Boolean done;
    private Long bonus;

    public TaskBean(TaskEntity taskEntity) {
        this.id = taskEntity.getId();
        this.title = taskEntity.getTitle();
        this.type = taskEntity.getType();
        this.frequency = taskEntity.getFrequency();
        this.value = taskEntity.getValue();
        this.quarant = taskEntity.getQuarant();
        this.from = taskEntity.getFrom();
        this.tomatoes = taskEntity.getTomatoes();
        this.startDate = taskEntity.getStartDate();
        this.endDate = taskEntity.getEndDate();
        this.done = taskEntity.getDone();
        this.bonus = taskEntity.getBonus();
        Optional.ofNullable(taskEntity.getQuestionnair()).ifPresent((questionnairEntity) -> {
            this.questionnairBean = QuestionnairBean.builder().questions(questionnairEntity
                    .getQuestions().stream().map((questionEntity) -> new QuestionBean(questionEntity)).collect(Collectors.toList()))
                    .id(questionnairEntity.getId()).build();
        });
    }


}
