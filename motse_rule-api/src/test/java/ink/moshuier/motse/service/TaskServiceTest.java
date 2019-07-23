package ink.moshuier.motse.service;

import ink.moshuier.motse.api.BaseTest;
import ink.moshuier.motse.dao.bean.PageBean;
import ink.moshuier.motse.dao.bean.TaskBean;
import ink.moshuier.motse.model.entity.TaskEntity;
import ink.moshuier.motse.model.enums.Quarants;
import ink.moshuier.motse.model.enums.TaskType;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.LocalTime;

import static ink.moshuier.motse.dao.util.TimeUtils.getMilliSecondOfDate;
import static org.assertj.core.api.Assertions.assertThat;

public class TaskServiceTest extends BaseTest {



    @Test
    public void addOrUpdateTask() {
        TaskBean taskBean = TaskBean.builder().title("游泳").type(TaskType.Habit).frequency(2).value(300L).quarant(Quarants.ImportantAndNotUrgent)
                .from(LocalTime.now().getSecond()).tomatoes(2).startDate(getMilliSecondOfDate(LocalDate.now()))
                .endDate(getMilliSecondOfDate(LocalDate.now())).bonus(200L).build();
        Long aLong = taskService.addOrUpdateTask(taskBean);
        System.out.println(aLong);
    }

    @Test
    public void getTasks() {
        PageBean<TaskBean, TaskEntity> tasks = taskService.getTasks(null, TaskType.Habit, PageRequest.of(0, 10));
        System.out.println(tasks.getContent());
        assertThat(tasks.getPageNum()).isEqualTo(0);

    }

    @Test
    public void getTask() {
        TaskBean task = taskService.getTask(taskId);
        assertThat(task.getId()).isEqualTo(taskId);
    }

    @Test
    public void deleteTask() {
        taskService.deleteTask(taskId);

        PageBean<TaskBean, TaskEntity> tasks = taskService.getTasks(null, null, PageRequest.of(0, 10));
        assertThat(tasks.getContent().size()).isEqualTo(1);
    }
}