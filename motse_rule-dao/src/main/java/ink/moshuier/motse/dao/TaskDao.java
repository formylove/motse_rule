package ink.moshuier.motse.dao;

import ink.moshuier.motse.dao.bean.PageBean;
import ink.moshuier.motse.dao.bean.TaskBean;
import ink.moshuier.motse.dao.exception.CommonException;
import ink.moshuier.motse.dao.exception.ErrorCode;
import ink.moshuier.motse.dao.repo.TaskEntityRepository;
import ink.moshuier.motse.model.entity.TaskEntity;
import ink.moshuier.motse.model.enums.Quarants;
import ink.moshuier.motse.model.enums.TaskType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Repository
@Transactional(readOnly = true)
public class TaskDao {
    @Autowired
    private  TaskEntityRepository taskEntityRepository;

    @Autowired
    public TaskDao(TaskEntityRepository taskEntityRepository) {
        this.taskEntityRepository = taskEntityRepository;
    }

    public Long addOrUpdateTask(TaskBean taskBean) {
        TaskEntity taskEntity = taskEntityRepository.saveAndFlush(Optional.ofNullable(taskBean).map(beanToEntity).get());
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
                    Path path_task_type = root.get("taskType");
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

        Page<TaskEntity> page = taskEntityRepository.findAll(specification, pageable);
        PageBean<TaskBean, TaskEntity> pageBean = new PageBean<TaskBean, TaskEntity>(page) {};

        return pageBean;
    }

    public TaskBean getTask(Long tid) {
        return new TaskBean(taskEntityRepository.findById(tid).orElseThrow(()->new CommonException(ErrorCode.TASK_NOT_FOUND)));
    }






    public void deleteTask(Long tid) {
        taskEntityRepository.deactiveStatus(tid);
    }
}
