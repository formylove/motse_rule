package ink.moshuier.motse.bean;

import ink.moshuier.motse.annotation.Projection;
import ink.moshuier.motse.enums.util.ConvertDirection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author : Sarah Xu
 * @date : 2019-07-12
 **/
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionnairBean extends BaseBean {
    private List<QuestionBean> questions;
    @Projection(ignoreIfNull = true, direction = ConvertDirection.FROM_BEAN_TO_ENTITY)
    private TaskBean taskBean;


}
