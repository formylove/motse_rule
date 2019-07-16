package ink.moshuier.motse.dao.bean;

import ink.moshuier.motse.model.entity.TaskEntity;
import ink.moshuier.motse.model.enums.Quarants;
import ink.moshuier.motse.model.enums.TaskType;
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
public class TaskBean {
    private Long id;
    private String title;
    private TaskType type;
    //执行频率
    private int frequency;
    //价值
    private Long value;
    private Quarants quarant;


    //固定时间段
    private Long from;
    private int tomatoes;

    private Long score;
    //任务开始时间
    private Long startDate;
    private Long endDate;
    private Boolean done;
    private Long bonus;

    public TaskBean(TaskEntity taskEntity) {
        this.title = taskEntity.getTitle();
        this.type = taskEntity.getType();
        this.frequency = taskEntity.getFrequency();
        this.value = taskEntity.getValue();
        this.quarant = taskEntity.getQuarant();
        this.from = taskEntity.getFrom();
        this.tomatoes = taskEntity.getTomatoes();
        this.score = taskEntity.getScore();
        this.startDate = taskEntity.getStartDate();
        this.endDate = taskEntity.getEndDate();
        this.done = taskEntity.getDone();
        this.bonus = taskEntity.getBonus();
    }


}
