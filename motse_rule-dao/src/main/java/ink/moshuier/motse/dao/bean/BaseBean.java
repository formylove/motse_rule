package ink.moshuier.motse.dao.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author : Sarah Xu
 * @date : 2019-07-12
 **/
@SuperBuilder
@Data
@NoArgsConstructor
public class BaseBean {
    protected Long id;
}
