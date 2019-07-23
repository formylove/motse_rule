package ink.moshuier.motse.api;

import ink.moshuier.motse.api.bean.PageResponseBean;
import ink.moshuier.motse.api.bean.ResponseBean;
import ink.moshuier.motse.api.bean.dto.QuestionnairDTO;
import ink.moshuier.motse.dao.bean.TaskBean;
import ink.moshuier.motse.model.enums.Quarants;
import ink.moshuier.motse.model.enums.TaskType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Api(value = "任务/长期任务")
@RestController
@RequestMapping("/tasks")
public interface TaskApi extends RestfulApi {
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public class TaskAddingRequest {
    @ApiModelProperty("名称")
    @NotBlank
    private String title;
    @ApiModelProperty("当天任务/习惯/长期任务")
    @NotNull
    private TaskType type;


    @ApiModelProperty("优先级")
    @NotNull
    private Quarants quarant;
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
    private int frequency;
    @ApiModelProperty("起始时间")
    @NotNull
    private Integer from;
    @ApiModelProperty("番茄时间")
    @NotNull
    private int tomatoes;
    @ApiModelProperty("开始日期")
    @NotNull
    private Long startDate;
    @ApiModelProperty("终止日期")
    private Long endDate;


  }


    @ApiOperation(value = "添加任务")
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseBean<Long> addTask(@Validated @RequestBody TaskAddingRequest addingRequest);


  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public class TaskUpdateRequest {

    @ApiModelProperty("名称")
    @NotBlank
    private String title;
    @ApiModelProperty("任务/长期任务")
    @NotNull
    private TaskType type;


    @ApiModelProperty("优先级")
    @NotNull
    private Quarants quarant;
    @ApiModelProperty("价值 价格/日")
    @NotNull
    private Long value;
    @ApiModelProperty("项目奖励")
    private Long bonus;



    //时间
    @ApiModelProperty("频率 单位: 天/次")
    @NotNull
    private int frequency;
    @ApiModelProperty("起始时间")
    @NotNull
    private Integer from;
    @ApiModelProperty("番茄时间")
    @NotNull
    private int tomatoes;
    @ApiModelProperty("开始日期")
    @NotNull
    private Long startDate;
    @ApiModelProperty("终止日期")
    private Long endDate;

  }

  @ApiOperation(value = "修改任务")
  @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseBean<Void> updateTask(@Validated @RequestBody TaskUpdateRequest updateRequest, @PathVariable Long id);


  @ApiOperation(value = "删除任务")
  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseBean<Void> deleteTask(@Validated @PathVariable Long hid);



  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public class TaskDTO {
    private Long id;

    private String title;
    private TaskType type;


    private Quarants quarant;
    private Long value;
    private Long bonus;



    private int frequency;
    private Integer from;
    private int tomatoes;
    private Long startDate;
    private Long endDate;
    private Boolean done;
    private Long score;


    public TaskDTO(TaskBean taskBean) {
      this.title = taskBean.getTitle();
      this.type = taskBean.getType();
      this.frequency = taskBean.getFrequency();
      this.value = taskBean.getValue();
      this.quarant = taskBean.getQuarant();
      this.from = taskBean.getFrom();
      this.tomatoes = taskBean.getTomatoes();
      this.score = taskBean.getScore();
      this.startDate = taskBean.getStartDate();
      this.endDate = taskBean.getEndDate();
      this.done = taskBean.getDone();
      this.bonus = taskBean.getBonus();
    }

  }
  @ApiOperation(value = "查询任务")
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseBean<TaskDTO> getTask(@Validated @PathVariable Long hid);




    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class TasksRequest {
      @ApiModelProperty("名称")
      @NotBlank
      private String title;
      @ApiModelProperty("当天任务/习惯/长期任务")
      @NotNull
      private TaskType type;


      @ApiModelProperty("页码")
      @NotNull
      private Integer pageNum;

      @ApiModelProperty("页大小")
      @NotNull
      private Integer pageSize;



    }
  @ApiOperation(value = "查询所有任务")
  @PostMapping(value = "/searches", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public PageResponseBean<TaskDTO, TaskBean> getTasks(@Validated @RequestBody TasksRequest tasksRequest);



}
