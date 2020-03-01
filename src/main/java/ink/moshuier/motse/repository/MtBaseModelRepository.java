package ink.moshuier.motse.repository;

import ink.moshuier.motse.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import javax.transaction.Transactional;
import java.util.Optional;

@NoRepositoryBean

public interface MtBaseModelRepository<E extends BaseEntity>
        extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> {


    @Query(" UPDATE #{#entityName} " +
            "SET activeStatus= " +
            "CASE activeStatus " +
            "WHEN TRUE THEN FALSE " +
            "WHEN FALSE THEN TRUE " +
            "ELSE activeStatus " +
            "END " +
            "WHERE id=?1 ")
    @Modifying(clearAutomatically = true)
    Integer toggleActiveStatus(Long id);

    @Query(" UPDATE #{#entityName} " +
            "SET activeStatus=false " +
            "WHERE id=?1 ")
    @Transactional
    @Modifying(clearAutomatically = true)
    Integer deactive(Long id);

    Optional<E> findOptionalByIdAndActiveStatusIsTrue(Long id);
}
