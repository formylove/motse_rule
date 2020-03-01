package ink.moshuier.motse.bean;

import ink.moshuier.motse.annotation.Projection;
import ink.moshuier.motse.enums.ConvertDirection;
import ink.moshuier.motse.enums.QuarantsEnum;
import ink.moshuier.motse.enums.TaskType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author : Sarah Xu
 * @date : 2019-07-12
 **/
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskBean extends BaseBean {
    private String title;
    private TaskType type;
    //执行频率
    private Integer frequency;
    //价值
    private Long value;
    private QuarantsEnum quarant;


    //固定时间段
    private Integer from;
    private Integer tomatoes;

    private Long score;
    @Projection(ignoreIfNull = true, direction = ConvertDirection.FROM_BEAN_TO_ENTITY)
    private QuestionnairBean questionnair;
    //任务开始时间
    private Long startDate;
    private Long endDate;
    private Boolean done;
    private Long bonus;


}
