package ink.moshuier.motse.api;

import ink.moshuier.motse.MotseApplication;
import ink.moshuier.motse.dao.QuestionDao;
import ink.moshuier.motse.dao.QuestionnairDao;
import ink.moshuier.motse.dao.bean.QuestionBean;
import ink.moshuier.motse.dao.bean.QuestionnairBean;
import ink.moshuier.motse.dao.bean.TaskBean;
import ink.moshuier.motse.model.enums.Quarants;
import ink.moshuier.motse.model.enums.TaskType;
import ink.moshuier.motse.service.QuestionnairService;
import ink.moshuier.motse.service.TaskService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import static ink.moshuier.motse.dao.util.TimeUtils.getMilliSecondOfDate;

/**
 * @Author: fate @Date: 2019/5/16 15:37 @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MotseApplication.class)
@Transactional
//@AutoConfigureTestDatabase
@ActiveProfiles("dev")
public class BaseTest {

    @Autowired
    protected WebApplicationContext context;
    protected MockMvc mockMvc;
    @Autowired
    protected QuestionnairDao questionnairDao;
    @Autowired
    protected QuestionDao questionDao;
    @Autowired
    protected QuestionnairService questionnairService;
    @Autowired
    protected TaskService taskService;


    public static Long taskId = null;
    protected static String TOKEN = null;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        //任务数据准备
        QuestionnairBean questionnairBean = QuestionnairBean.builder().questions(Arrays.asList(
                QuestionBean.builder().proportion(20).question("是否走神").build(),
                QuestionBean.builder().proportion(80).question("思维导图是否完成").build()
        )).build();

        TaskBean taskBean = TaskBean.builder().title("社科书籍").type(TaskType.Habit).frequency(2).value(300L).quarant(Quarants.ImportantAndNotUrgent)
                .from(LocalTime.now().getSecond()).tomatoes(2).startDate(getMilliSecondOfDate(LocalDate.now()))
                .endDate(getMilliSecondOfDate(LocalDate.now())).bonus(30L).questionnairBean(questionnairBean).build();

        taskService.addOrUpdateTask(taskBean);
        taskBean = TaskBean.builder().title("热敷脖子").type(TaskType.Habit).frequency(2).value(300L).quarant(Quarants.ImportantAndNotUrgent)
                .from(LocalTime.now().getSecond()).tomatoes(2).startDate(getMilliSecondOfDate(LocalDate.now()))
                .endDate(getMilliSecondOfDate(LocalDate.now())).bonus(10L).build();
        taskId = taskService.addOrUpdateTask(taskBean);


    }
}
