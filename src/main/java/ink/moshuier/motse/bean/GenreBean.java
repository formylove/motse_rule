package ink.moshuier.motse.bean;

import ink.moshuier.motse.enums.CardCategoryEnum;
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
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GenreBean extends BaseBean {
    private List<String> cname;
    private List<String> ename;
    private List<String> portraits;
    private String desc;
    private Long parentId;
    private Integer level;
    private CardCategoryEnum cardCategory;
}
