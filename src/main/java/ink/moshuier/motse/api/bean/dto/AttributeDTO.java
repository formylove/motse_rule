package ink.moshuier.motse.api.bean.dto;

import ink.moshuier.motse.enums.CardCategoryEnum;
import ink.moshuier.motse.enums.DimensionEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author : Sarah Xu
 * @date : 2020-02-26
 **/
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AttributeDTO extends BaseDTO {
    @ApiModelProperty("名称")
    @NotBlank
    private String name;

    @ApiModelProperty("量纲")
    @NotNull
    private DimensionEnum dimension;

    @ApiModelProperty("卡片类型")
    @NotNull
    private CardCategoryEnum cardCategory;

}
