package ink.moshuier.motse.entity;

import ink.moshuier.motse.enums.QuarantsEnum;
import ink.moshuier.motse.enums.TaskTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.CascadeType.ALL;


@Data
@Entity
@Table(name = "motse_task")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TaskEntity extends BaseEntity implements Serializable {
    @Column(name = "title", columnDefinition = "VARCHAR(50)", nullable = false)
    private String title;
    @Column(name = "type", columnDefinition = "TINYINT", nullable = false)
    private TaskTypeEnum type;
    //执行频率
    @Column(name = "frequency", columnDefinition = "TINYINT", nullable = false)
    private Integer frequency;
    //重要性
    @Column(name = "value", columnDefinition = "INT", nullable = false)
    private Long value;

    @Column(name = "quarant", columnDefinition = "TINYINT", nullable = false)
    private QuarantsEnum quarant;

    //固定时间段
    @Column(name = "sfrom", columnDefinition = "BIGINT")
    private Integer from;
    @Column(name = "tomatoes", columnDefinition = "TINYINT", nullable = false)
    private Integer tomatoes;

    @OneToOne(targetEntity = QuestionnairEntity.class, cascade = ALL)
    @JoinColumn(name = "questionnair_id", referencedColumnName = "id")
    private QuestionnairEntity questionnair;


    //任务开始日期
    @Column(name = "startDate", columnDefinition = "BIGINT", nullable = false)
    private Long startDate;
    @Column(name = "endDate", columnDefinition = "BIGINT", nullable = false)
    private Long endDate;
    //false 为失败
    @Column(name = "done", columnDefinition = "CHAR(1)")
    private Boolean done;
    @Column(name = "bonus", columnDefinition = "INT", nullable = false)
    private Long bonus;


}
