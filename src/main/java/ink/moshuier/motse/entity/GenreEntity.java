package ink.moshuier.motse.entity;

import ink.moshuier.motse.converter.JsonStringListConverter;
import ink.moshuier.motse.enums.CardCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "motse_genre")
public class GenreEntity extends BaseEntity implements Serializable {
    @Column(name = "cname", columnDefinition = "VARCHAR(500)", nullable = false)
    @Convert(converter = JsonStringListConverter.class)
    private List<String> cname;

    @Column(name = "ename", columnDefinition = "VARCHAR(500)", nullable = false)
    @Convert(converter = JsonStringListConverter.class)
    private List<String> ename;

    @Column(name = "portraits", columnDefinition = "VARCHAR(500)")
    @Convert(converter = JsonStringListConverter.class)
    private List<String> portraits;

    @Column(name = "parent_id", columnDefinition = "BIGINT")
    private Long parentId;

    @Column(name = "level", columnDefinition = "INT", nullable = false)
    private Integer level;

    @Column(name = "card_category", columnDefinition = "VARCHAR(500)", nullable = false)
    @Convert(converter = CardCategoryEnum.Converter.class)
    private CardCategoryEnum cardCategory;

    @Column(name = "description", columnDefinition = "TEXT")
    private String desc;
}
