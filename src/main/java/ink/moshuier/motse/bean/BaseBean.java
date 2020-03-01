package ink.moshuier.motse.bean;

import ink.moshuier.motse.annotation.Projection;
import ink.moshuier.motse.enums.ConvertDirection;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

/**
 * @author : Sarah Xu
 * @date : 2019-07-12
 **/
@SuperBuilder
@Data
@NoArgsConstructor
public class BaseBean {
    protected Long id;
    @Projection(direction = ConvertDirection.FROM_BEAN_TO_ENTITY, ignoreIfNull = true)
    protected Instant creationDate;
    @Projection(direction = ConvertDirection.FROM_BEAN_TO_ENTITY, ignoreIfNull = true)
    protected Instant lastUpdateDate;
    @Projection(direction = ConvertDirection.FROM_BEAN_TO_ENTITY, ignoreIfNull = true)
    protected Boolean activeStatus;
}
