package ink.moshuier.motse.bean;

import ink.moshuier.motse.enums.UnitTypeEnum;
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
public class UnitBean extends BaseBean {
    private List<String> cname;
    private List<String> ename;
    private String symbol;
    private UnitTypeEnum unitType;
    private String conversion;
}
