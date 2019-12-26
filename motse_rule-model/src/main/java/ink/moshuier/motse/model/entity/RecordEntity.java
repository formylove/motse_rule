package ink.moshuier.motse.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Entity
@Table(name = "motse_record")
@NoArgsConstructor
@AllArgsConstructor
public class RecordEntity extends BaseEntity implements Serializable {

    @Column(name = "task_id", columnDefinition = "BIGINT")
    private Long taskId;

    //哪一天的表现
    @Column(name = "task_date", columnDefinition = "BIGINT")
    private Long taskDate;

    //评价
    @Column(name = "score", columnDefinition = "INT")
    private int score;

    //单价
    @Column(name = "value", columnDefinition = "BIGINT")
    private Long value;

    //收益
    @Column(name = "earning", columnDefinition = "BIGINT")
    private Long earning;

    //是否是系统默认打分
    @Column(name = "default_score", columnDefinition = "CHAR(1)")
    private Boolean isDefaultScore=true;

}
