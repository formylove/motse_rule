package ink.moshuier.motse.api.service;

import ink.moshuier.motse.MotseApplication;
import ink.moshuier.motse.api.BaseTest;
import ink.moshuier.motse.dao.bean.QuestionBean;
import ink.moshuier.motse.dao.bean.QuestionnairBean;
import ink.moshuier.motse.model.entity.QuestionEntity;
import ink.moshuier.motse.model.entity.QuestionnairEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Sarah Xu
 * @date : 2019-07-15
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MotseApplication.class)
//@Transactional
//@AutoConfigureTestDatabase
//@ActiveProfiles("default")
public class QuestionnairServiceTest extends BaseTest {

    @Test
    public void saveTest() {
        QuestionnairEntity questionnairEntity = QuestionnairEntity.builder().build();
        QuestionEntity questionEntity = QuestionEntity.builder().question("你是谁").proportion(20).questionnair(questionnairEntity).build();
        QuestionEntity questionEntity2 = QuestionEntity.builder().question("你妈贵姓").proportion(20).questionnair(questionnairEntity).build();


        //被引用的对象需要先保存
        questionnairEntity = questionnairEntityRepository.saveAndFlush(questionnairEntity);
        questionEntity = questionEntityRepository.saveAndFlush(questionEntity);
        System.out.println(questionEntity);
        System.out.println(questionnairEntity);
    }
//要颜值要才华要学历，我觉得李健更适合你，彭于晏都不合格的
    @Test
    public void saveQuestionnairTest() {
        QuestionnairBean questionnairBean = new QuestionnairBean();
        List<QuestionBean> questionBeanList = new ArrayList<>();
        QuestionBean 什么情况 = QuestionBean.builder().question("什么情况").proportion(20).build();
        QuestionBean 啥 = QuestionBean.builder().question("哪里?").proportion(20).build();
        questionBeanList.add(什么情况);
        questionBeanList.add(啥);
        questionnairBean.setQuestions(questionBeanList);

        System.out.println(questionnairService.addQuestionnair(questionnairBean));


    }
}
