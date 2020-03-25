package ink.moshuier.motse.api;

import ink.moshuier.motse.annotation.ControllerDefiner;
import ink.moshuier.motse.api.bean.dto.TaskDTO;
import ink.moshuier.motse.bean.TaskBean;
import ink.moshuier.motse.service.TaskService;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@ControllerDefiner(value = "任务/长期任务", path = "/tasks")
public class TaskController extends RestfulController<TaskDTO, TaskBean, TaskService> {


}
