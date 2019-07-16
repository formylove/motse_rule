package ink.moshuier.motse.dao.repo;

import ink.moshuier.motse.model.entity.TaskEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Repository
public interface TaskEntityRepository
    extends BaseEntityRepository<TaskEntity> {

}
