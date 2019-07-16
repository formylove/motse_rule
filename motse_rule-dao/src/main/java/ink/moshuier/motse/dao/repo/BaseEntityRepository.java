package ink.moshuier.motse.dao.repo;

import ink.moshuier.motse.dao.util.IdGenerator;
import ink.moshuier.motse.model.entity.BaseEntity;
import net.jodah.typetools.TypeResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

@NoRepositoryBean
public interface BaseEntityRepository<E extends BaseEntity>
    extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> {


  default long genUuid() {
    return IdGenerator.getId();

  }

  @SuppressWarnings("unchecked")
  default E newEntity() {
    Type repoType =
        ((Class<? extends BaseEntityRepository<E>>) this.getClass().getGenericInterfaces()[0])
            .getGenericInterfaces()[0];

    Class<E> entityClass =
        (Class<E>) TypeResolver.resolveRawArgument(repoType, BaseEntityRepository.class);
    try {
      E e = entityClass.getConstructor().newInstance();
      e.setId(genUuid());
      return e;
    } catch (InstantiationException
        | IllegalAccessException
        | InvocationTargetException
        | NoSuchMethodException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  @Query(
      " UPDATE #{#entityName} "
          + "SET activeStatus= "
          + "CASE activeStatus "
          + "WHEN TRUE THEN FALSE "
          + "WHEN FALSE THEN TRUE "
          + "ELSE activeStatus "
          + "END "
          + "WHERE id=?1 ")
  @Modifying(clearAutomatically = true)
  int toggleActiveStatus(Long id);

  /**
   * Override hard delete to soft delete
   */
  @Query(
      " UPDATE #{#entityName} " + "SET activeStatus=false " + "WHERE id=?1 AND activeStatus=TRUE ")
  @Modifying(clearAutomatically = true)
  int deactiveStatus(Long id);


  /**
   * Override hard delete to soft delete
   *
   * @param id
   * @return
   */
  @Query("FROM #{#entityName} WHERE id=?1 AND activeStatus=TRUE ")
  E getActiveEntityById(Long id);
}
