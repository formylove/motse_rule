package ink.moshuier.motse.api.bean.dto;

import ink.moshuier.motse.enums.DimensionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author : Sarah Xu
 * @date : 2020-02-26
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
public class UnitDTO extends BaseDTO {
    private List<String> cname;
    private List<String> ename;
    private String symbol;
    private DimensionEnum unitType;
    private String conversion;

}