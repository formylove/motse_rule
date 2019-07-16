package ink.moshuier.motse.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "motse_questionnair")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class QuestionnairEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -2127638796847271526L;
    @OneToMany(mappedBy = "questionnair_id")
    List<QuestionEntity> questions = new ArrayList<>();
}
