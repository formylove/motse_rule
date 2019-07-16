package ink.moshuier.motse.model.entity;

import ink.moshuier.motse.model.enums.Quarants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Entity
@Table(name = "motse_mark")
@NoArgsConstructor
@AllArgsConstructor
public class MarkEntity extends BaseEntity implements Serializable {

    @OneToOne(targetEntity = TaskEntity.class)
    @JoinColumn(name = "taskId",referencedColumnName = "id")
    private TaskEntity taskEntity;

    private Long taskDate;
    //评价
    private int score;
    //重要性
    private Long value;
    private Quarants quarant;
    //是否是系统默认打分
    private Boolean isDefaultScore=true;





}
