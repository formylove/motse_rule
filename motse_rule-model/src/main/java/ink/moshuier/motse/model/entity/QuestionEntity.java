package ink.moshuier.motse.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "motse_question")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class QuestionEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -2127638796847271526L;
    @JoinColumn(name = "questionnair_id",referencedColumnName = "id")
    @ManyToOne(targetEntity = QuestionnairEntity.class)
    private QuestionnairEntity questionnair;
    @Column(name = "question",columnDefinition = "varchar(100)",nullable = false)
    private String question;
    @Column(name = "proportion",columnDefinition = "tinyint",nullable = false)
    private int proportion;
}
