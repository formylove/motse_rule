package ink.moshuier.motse.api.impl;

import ink.moshuier.motse.api.QuestionnairApi;
import ink.moshuier.motse.api.bean.ResponseBean;
import ink.moshuier.motse.dao.bean.QuestionBean;
import ink.moshuier.motse.dao.bean.QuestionnairBean;
import ink.moshuier.motse.service.QuestionnairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.stream.Collectors;

/**
 * @author : Sarah Xu
 * @date : 2019-07-16
 **/
@Controller
public class QuestionnairApiImpl implements QuestionnairApi {
    @Autowired
    QuestionnairService questionnairService;
    @Override
    public ResponseBean<Long> addQuestionnair(QuestionnairRequest questionnairRequest) {
        QuestionnairBean questionnairBean = QuestionnairBean.builder().id(questionnairRequest.getTaskId()).questions(
                questionnairRequest.getQuestions().stream().map((questionDTO) ->
                        QuestionBean.builder().proportion(questionDTO.getProportion()).question(questionDTO.getQuestion()).build()).collect(Collectors.toList())
        ).build();
        Long questionnairId = questionnairService.addQuestionnair(questionnairBean);
        return new ResponseBean<>(questionnairId);
    }
}
