package ink.moshuier.motse.bean;

import ink.moshuier.motse.annotation.Projection;
import ink.moshuier.motse.enums.CardCategoryEnum;
import ink.moshuier.motse.enums.DimensionEnum;
import ink.moshuier.motse.enums.util.ConvertDirection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author : Sarah Xu
 * @date : 2019-07-12
 **/
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttributeBean extends BaseBean {
    private String name;
    private DimensionEnum dimension;
    @Projection(direction = ConvertDirection.FROM_BEAN_TO_ENTITY, ignoreIfNull = true)
    private CardCategoryEnum cardCategory;
}
