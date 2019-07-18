package ink.moshuier.motse.service;


import ink.moshuier.motse.api.BaseTest;
import ink.moshuier.motse.dao.bean.TaskBean;
import ink.moshuier.motse.model.enums.Quarants;
import ink.moshuier.motse.model.enums.TaskType;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static ink.moshuier.motse.dao.util.TimeUtils.getMilliSecondOfDate;

public class TaskServiceTest extends BaseTest {



    @Test
    public void addOrUpdateTask() {
        TaskBean taskBean = TaskBean.builder().title("社科书籍").type(TaskType.Habit).frequency(2).value(300L).quarant(Quarants.ImportantAndNotUrgent)
                .from(LocalTime.now().getSecond()).tomatoes(2).startDate(getMilliSecondOfDate(LocalDate.now()))
                .endDate(getMilliSecondOfDate(LocalDate.now())).bonus(200L).build();
        Long aLong = taskService.addOrUpdateTask(taskBean);
        System.out.println(aLong);

    }

    @Test
    public void getTasks() {
    }

    @Test
    public void getTask() {
    }

    @Test
    public void deleteTask() {
    }
}