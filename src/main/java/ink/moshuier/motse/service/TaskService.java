package ink.moshuier.motse.service;

import ink.moshuier.motse.bean.TaskBean;
import ink.moshuier.motse.entity.TaskEntity;
import ink.moshuier.motse.repository.TaskRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Service
@Transactional
public class TaskService extends BaseDao<TaskBean, TaskEntity, TaskRepository> {


}

