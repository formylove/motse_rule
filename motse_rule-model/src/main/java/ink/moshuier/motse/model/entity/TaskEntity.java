package ink.moshuier.motse.model.entity;

import ink.moshuier.motse.model.enums.Quarants;
import ink.moshuier.motse.model.enums.TaskType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "motse_task")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TaskEntity extends BaseEntity implements Serializable {
    @Column(name = "title",columnDefinition = "varchar(50)",nullable = false)
    private String title;
    @Column(name = "type",columnDefinition = "tinyint",nullable = false)
    private TaskType type;
    //执行频率
    @Column(name = "frequency",columnDefinition = "tinyint",nullable = false)
    private int frequency;
    //重要性
    @Column(name = "value",columnDefinition = "int",nullable = false)
    private Long value;
    @Column(name = "quarant",columnDefinition = "tinyint",nullable = false)
    private Quarants quarant;

    //固定时间段
    @Column(name = "sfrom",columnDefinition = "bigint")
    private Long from;
    @Column(name = "tomatoes",columnDefinition = "tinyint",nullable = false)
    private int tomatoes;

    @OneToOne(targetEntity = QuestionnairEntity.class)
    @JoinColumn(name = "questionnair_id",referencedColumnName = "id")
    private QuestionnairEntity questionnair;


    //任务开始日期
    @Column(name = "startDate",columnDefinition = "bigint",nullable = false)
    private Long startDate;
    @Column(name = "endDate",columnDefinition = "bigint",nullable = false)
    private Long endDate;
    //false 为失败
    @Column(name = "done",columnDefinition = "char(1)")
    private Boolean done;
    @Column(name = "bonus",columnDefinition = "int",nullable = false)
    private Long bonus;


}
