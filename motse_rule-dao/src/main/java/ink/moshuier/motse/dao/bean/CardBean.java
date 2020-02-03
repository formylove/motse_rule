package ink.moshuier.motse.dao.bean;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardBean extends BaseBean {
    private List<String> cname;
    private List<String> ename;
    private List<String> portraits;
    private String desc;
}
