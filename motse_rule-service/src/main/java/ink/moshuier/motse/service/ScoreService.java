package ink.moshuier.motse.service;

import ink.moshuier.motse.dao.TaskDao;
import ink.moshuier.motse.dao.exception.CommonException;
import ink.moshuier.motse.model.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static ink.moshuier.motse.dao.exception.ErrorCode.TASK_NOT_FOUND;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Service
public class ScoreService {
  @Value("${algorithm.parity}")
  private float parity;
  @Autowired
  private TaskDao taskDao;

  public Long caculateEarning(Long taskId, Integer score) {
    TaskEntity taskEntity = Optional.ofNullable(taskDao.getActiveEntityById(taskId)).orElseThrow(() -> new CommonException(TASK_NOT_FOUND));
    return (long) (taskEntity.getValue() * parity * score);

  }
}