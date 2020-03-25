package ink.moshuier.motse.entity;

import ink.moshuier.motse.enums.CardCategoryEnum;
import ink.moshuier.motse.enums.DimensionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Entity
@Table(name = "motse_attribute")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AttributeEntity extends BaseEntity implements Serializable {
    @Column(name = "name", columnDefinition = "VARCHAR(500)", nullable = false)
    private String name;

    @Column(name = "dimension", columnDefinition = "VARCHAR(500)", nullable = false)
    @Convert(converter = DimensionEnum.Converter.class)
    private DimensionEnum dimension;

    @Column(name = "cardCategory", columnDefinition = "VARCHAR(500)")
    @Convert(converter = CardCategoryEnum.Converter.class)
    private CardCategoryEnum cardCategory;
}
