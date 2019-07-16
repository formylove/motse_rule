package ink.moshuier.motse.api.dao;

import ink.moshuier.motse.MotseApplication;
import ink.moshuier.motse.api.BaseTest;
import ink.moshuier.motse.model.entity.QuestionEntity;
import ink.moshuier.motse.model.entity.QuestionnairEntity;
import org.apache.poi.sl.usermodel.ObjectMetaData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Sarah Xu
 * @date : 2019-07-15
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MotseApplication.class)
//@Transactional
//@AutoConfigureTestDatabase
//@ActiveProfiles("default")
public class QuestionnairDaoTest extends BaseTest {

    @Test
    public void saveTest() {
        QuestionnairEntity questionnairEntity = QuestionnairEntity.builder().build();
        QuestionEntity questionEntity = QuestionEntity.builder().question("你是谁").proportion(20).questionnair(questionnairEntity).build();
        QuestionEntity questionEntity2 = QuestionEntity.builder().question("你妈贵姓").proportion(20).questionnair(questionnairEntity).build();




        questionEntity = questionEntityRepository.saveAndFlush(questionEntity);
        questionnairEntity = questionnairEntityRepository.saveAndFlush(questionnairEntity);
        System.out.println(questionEntity);
        System.out.println(questionnairEntity);
    }


}
