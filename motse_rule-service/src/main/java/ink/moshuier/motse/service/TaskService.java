package ink.moshuier.motse.service;

import ink.moshuier.motse.dao.TaskDao;
import ink.moshuier.motse.dao.bean.PageBean;
import ink.moshuier.motse.dao.bean.TaskBean;
import ink.moshuier.motse.dao.exception.CommonException;
import ink.moshuier.motse.model.entity.TaskEntity;
import ink.moshuier.motse.model.enums.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static ink.moshuier.motse.dao.exception.ErrorCode.TASK_NOT_FOUND;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Service
@Transactional
public class TaskService {
  @Autowired
  private TaskDao taskDao;

    public Long addOrUpdateTask(TaskBean taskBean) {
      TaskEntity taskEntity = taskDao.saveAndFlush(Optional.ofNullable(taskBean).map(beanToEntity).get());
      return taskEntity.getId();
    }

    Function<TaskBean,TaskEntity> beanToEntity = (bean)->{
      TaskEntity taskEntity = TaskEntity.builder().title(bean.getTitle()).type(bean.getType()).frequency(bean.getFrequency()).value(bean.getValue()).quarant(bean.getQuarant())
              .from(bean.getFrom()).tomatoes(bean.getTomatoes()).startDate(bean.getStartDate()).endDate(bean.getEndDate()).done(bean.getDone()).bonus(bean.getBonus()).build();
      taskEntity.setId(bean.getId());
      return  taskEntity;
    };


    public PageBean<TaskBean, TaskEntity> getTasks(String title, TaskType taskType, Pageable pageable) {

      Specification<TaskEntity> specification =
              (Root<TaskEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                Path path_task_type = root.get("type");
                Path path_title = root.get("title");

                Optional.ofNullable(taskType).ifPresent((type)->predicates.add(cb.in(path_task_type).value(type)));
                if (!StringUtils.isEmpty(title)) {
                  predicates.add(cb.like(path_title, title));
                }

                Predicate[] p = new Predicate[predicates.size()];
                criteriaQuery.where(cb.and(predicates.toArray(p)));
                criteriaQuery.orderBy(cb.desc(root.get("creationDate")));
                return predicates.isEmpty() ? cb.conjunction() : criteriaQuery.getRestriction();
              };

      Page<TaskEntity> page = taskDao.findAll(specification, pageable);
      PageBean<TaskBean, TaskEntity> pageBean = new PageBean<TaskBean, TaskEntity>(page) {};

      return pageBean;
    }

    public TaskBean getTask(Long tid) {
      TaskBean taskBean = new TaskBean(taskDao.findById(tid).orElseThrow(() -> new CommonException(TASK_NOT_FOUND)));
      return taskBean;
    }






    public void deleteTask(Long tid) {
      taskDao.deactiveStatus(tid);
    }
  }

