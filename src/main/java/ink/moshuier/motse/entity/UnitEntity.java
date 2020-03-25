package ink.moshuier.motse.entity;

import ink.moshuier.motse.enums.DimensionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "motse_unit")
public class UnitEntity extends BaseEntity implements Serializable {
    @ElementCollection
    @Column(name = "cname", columnDefinition = "VARCHAR(500)", nullable = false)
    private List<String> cname;

    @ElementCollection
    @Column(name = "ename", columnDefinition = "VARCHAR(500)")
    private List<String> ename;

    @Column(name = "symbol", columnDefinition = "VARCHAR(500)")
    private String symbol;

    @Column(name = "unit_type", columnDefinition = "VARCHAR(50)", nullable = false)
    @Convert(converter = DimensionEnum.Converter.class)
    private DimensionEnum unitType;

    @Column(name = "conversion", columnDefinition = "varchar(500)")
    private String conversion;
}
