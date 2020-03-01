package ink.moshuier.motse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;


@Data
@Entity
@Table(name = "motse_card")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CardEntity extends BaseEntity implements Serializable {
    @ElementCollection
    @Column(name = "cname", columnDefinition = "VARCHAR(500)", nullable = false)
    private List<String> cname;

    @Column(name = "ename", columnDefinition = "VARCHAR(500)")
    @ElementCollection
    private List<String> ename;

    @ElementCollection
    @Column(name = "value", columnDefinition = "VARCHAR(500)", nullable = false)
    private List<String> portraits;

    @Column(name = "description", columnDefinition = "VARCHAR(500)")
    private String desc;


}
