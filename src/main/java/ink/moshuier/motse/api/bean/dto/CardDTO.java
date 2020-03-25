package ink.moshuier.motse.api.bean.dto;

import ink.moshuier.motse.enums.CardCategoryEnum;
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
public class CardDTO extends BaseDTO {
    private List<String> cname;
    private List<String> ename;
    private List<String> portraits;
    private String desc;
    private CardCategoryEnum cardCategoryEnum;

}