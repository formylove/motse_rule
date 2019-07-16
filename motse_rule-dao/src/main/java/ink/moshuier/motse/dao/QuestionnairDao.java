package ink.moshuier.motse.dao;

import ink.moshuier.motse.dao.bean.PageBean;
import ink.moshuier.motse.dao.bean.QuestionnairBean;
import ink.moshuier.motse.dao.bean.TaskBean;
import ink.moshuier.motse.dao.exception.CommonException;
import ink.moshuier.motse.dao.exception.ErrorCode;
import ink.moshuier.motse.dao.repo.QuestionEntityRepository;
import ink.moshuier.motse.dao.repo.QuestionnairEntityRepository;
import ink.moshuier.motse.dao.repo.TaskEntityRepository;
import ink.moshuier.motse.model.entity.TaskEntity;
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
public class QuestionnairDao {
    @Autowired
    private QuestionnairEntityRepository questionnairEntityRepository;
    @Autowired
    private QuestionEntityRepository questionEntityRepository;

    public QuestionnairBean create(QuestionnairBean questionnairBean){

        return null;
    }






}
