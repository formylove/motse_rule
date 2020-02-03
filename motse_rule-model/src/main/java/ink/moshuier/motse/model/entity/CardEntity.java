package ink.moshuier.motse.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
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
    @Column(name = "cname", columnDefinition = "VARCHAR(500)", nullable = false)
    private List<String> cname;
    @Column(name = "ename", columnDefinition = "VARCHAR(500)")
    private List<String> ename;
    @Column(name = "value", columnDefinition = "INT", nullable = false)
    private List<String> portraits;
    @Column(name = "description", columnDefinition = "VARCHAR(500)")
    private String desc;


}
