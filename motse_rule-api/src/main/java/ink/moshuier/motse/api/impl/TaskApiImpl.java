package ink.moshuier.motse.api.impl;

import ink.moshuier.motse.api.TaskApi;
import ink.moshuier.motse.api.bean.PageResponseBean;
import ink.moshuier.motse.api.bean.ResponseBean;
import ink.moshuier.motse.dao.bean.PageBean;
import ink.moshuier.motse.dao.bean.TaskBean;
import ink.moshuier.motse.model.entity.TaskEntity;
import ink.moshuier.motse.model.enums.Quarants;
import ink.moshuier.motse.model.enums.TaskType;
import ink.moshuier.motse.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

/**
 * @author : Sarah Xu
 * @date : 2019-07-12
 **/
@Controller
public class TaskApiImpl implements TaskApi {
    @Autowired
    TaskService taskService;

    @Override
    public ResponseBean<Long> addTask(TaskAddingRequest addingRequest) {
        TaskBean taskBean = TaskBean.builder().title(addingRequest.getTitle()).type(addingRequest.getType()).frequency(addingRequest.getFrequency()).value(addingRequest.getValue())
                .quarant(addingRequest.getQuarant()).from(addingRequest.getFrom()).tomatoes(addingRequest.getTomatoes()).startDate(addingRequest.getStartDate()).endDate(addingRequest.getEndDate())
                .done(false).bonus(addingRequest.getBonus()).build();
        return new ResponseBean<>(taskService.addOrUpdateTask(taskBean));
    }

    @Override
    public ResponseBean<Void> updateTask(TaskUpdateRequest updateRequest) {
        TaskBean taskBean = TaskBean.builder().title(updateRequest.getTitle()).type(updateRequest.getType()).frequency(updateRequest.getFrequency()).value(updateRequest.getValue())
                .quarant(updateRequest.getQuarant()).from(updateRequest.getFrom()).tomatoes(updateRequest.getTomatoes()).startDate(updateRequest.getStartDate()).endDate(updateRequest.getEndDate())
                .done(false).bonus(updateRequest.getBonus()).build();
        taskService.addOrUpdateTask(taskBean);
        return new ResponseBean<>();
    }

    @Override
    public ResponseBean<Void> deleteTask(Long tid) {
        taskService.deleteTask(tid);
        return new ResponseBean<>();
    }

    @Override
    public ResponseBean<TaskDTO> getTask(Long tid) {
        return new ResponseBean<>(new TaskDTO(taskService.getTask(tid)));
    }

    @Override
    public PageResponseBean<TaskDTO, TaskBean> getTasks(TasksRequest tasksRequest) {
        PageBean<TaskBean, TaskEntity> tasks = taskService.getTasks(tasksRequest.getTitle(), tasksRequest.getType(), PageRequest.of(tasksRequest.getPageNum(), tasksRequest.getPageSize()));
        return new PageResponseBean<TaskDTO, TaskBean>(tasks){};
    }
}
