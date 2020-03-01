package ink.moshuier.motse.api.bean.dto;

import ink.moshuier.motse.enums.QuarantsEnum;
import ink.moshuier.motse.enums.TaskType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : Sarah Xu
 * @date : 2020-02-26
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO extends BaseDTO {
    @ApiModelProperty("名称")
    @NotBlank
    private String title;

    @ApiModelProperty("当天任务/习惯/长期任务")
    @NotNull
    private TaskType type;


    @ApiModelProperty("优先级")
    @NotNull
    private QuarantsEnum quarant;

    @ApiModelProperty("价值")
    @NotNull
    private Long value;

    @ApiModelProperty("项目奖励")
    private Long bonus;

    @ApiModelProperty("问卷")
    @NotNull
    private QuestionnairDTO questionnair;


    //时间
    @ApiModelProperty("频率 单位: 天/次")
    @NotNull
    private Integer frequency;

    @ApiModelProperty("起始时间")
    @NotNull
    private Integer from;

    @ApiModelProperty("番茄时间")
    @NotNull
    private Integer tomatoes;

    @ApiModelProperty("开始日期")
    @NotNull
    private Long startDate;

    @ApiModelProperty("终止日期")
    private Long endDate;

}
