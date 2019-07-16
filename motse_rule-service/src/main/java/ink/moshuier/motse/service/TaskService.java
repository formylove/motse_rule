package ink.moshuier.motse.service;

import ink.moshuier.motse.dao.TaskDao;
import ink.moshuier.motse.dao.bean.PageBean;
import ink.moshuier.motse.dao.bean.TaskBean;
import ink.moshuier.motse.model.entity.TaskEntity;
import ink.moshuier.motse.model.enums.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Service
public class TaskService {
  @Autowired
  private TaskDao taskDao;

  public Long addTask(TaskBean taskBean) {
    Long id = taskDao.addOrUpdateTask(taskBean);
    return id;
  }


  public PageBean<TaskBean, TaskEntity> getTasks(String title, TaskType taskType, Pageable pageable) {
    return taskDao.getTasks(title, taskType, pageable);
  }


  public TaskBean getTask(Long tid) {
    return taskDao.getTask(tid);
  }

  public void updateTask(TaskBean taskBean) {
    taskDao.addOrUpdateTask(taskBean);
  }

  public void deleteTask(Long tid) {
    taskDao.deleteTask(tid);
  }

}