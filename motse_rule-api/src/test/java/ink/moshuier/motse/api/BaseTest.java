package ink.moshuier.motse.api;

import ink.moshuier.motse.MotseApplication;
import ink.moshuier.motse.dao.QuestionDao;
import ink.moshuier.motse.dao.QuestionnairDao;
import ink.moshuier.motse.service.QuestionnairService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Author: fate @Date: 2019/5/16 15:37 @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MotseApplication.class)
@ActiveProfiles("dev")
public class BaseTest {

    @Autowired
    protected WebApplicationContext context;
    protected MockMvc mockMvc;
    @Autowired
    protected QuestionnairDao questionnairEntityRepository;
    @Autowired
    protected QuestionDao questionEntityRepository;
    @Autowired
    protected QuestionnairService questionnairService;

    protected static String TOKEN = null;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}
