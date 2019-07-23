package ink.moshuier.motse.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;


@Data
@Entity
@Table(name = "motse_questionnair")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class QuestionnairEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -2127638796847271526L;
    @OneToMany(targetEntity = QuestionEntity.class,cascade = ALL)
    @JoinColumn(name = "questionnair_id",referencedColumnName = "id")
    private List<QuestionEntity> questions = new ArrayList<>();

    @OneToOne(mappedBy = "questionnair", cascade = ALL)
    @JoinColumn(name = "questionnair_id",referencedColumnName = "id")
    private TaskEntity taskEntity;
}
