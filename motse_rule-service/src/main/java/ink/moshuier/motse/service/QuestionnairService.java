package ink.moshuier.motse.service;

import ink.moshuier.motse.dao.QuestionnairDao;
import ink.moshuier.motse.dao.bean.QuestionnairBean;
import ink.moshuier.motse.model.entity.QuestionEntity;
import ink.moshuier.motse.model.entity.QuestionnairEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Service
public class QuestionnairService {
  @Autowired
  private QuestionnairDao questionnairDao;

  public Long addQuestionnair(QuestionnairBean questionnairBean){
    QuestionnairEntity questionnairEntity = questionnairDao.saveAndFlush(Optional.ofNullable(questionnairBean).map(beanToEntity).get());
    return questionnairEntity.getId();
  }
Function<QuestionnairBean, QuestionnairEntity> beanToEntity = bean->
  QuestionnairEntity.builder().questions(bean.getQuestions().stream()
          .map(questionBean -> QuestionEntity.builder()
                  .question(questionBean.getQuestion()).proportion(questionBean.getProportion()).build()).collect(Collectors.toList()))
          .build();

}