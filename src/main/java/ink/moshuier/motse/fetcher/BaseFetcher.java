package ink.moshuier.motse.fetcher;

import ink.moshuier.motse.bean.BaseBean;
import ink.moshuier.motse.bean.BeanPage;
import ink.moshuier.motse.bean.SearchCondition;
import ink.moshuier.motse.entity.BaseEntity;
import ink.moshuier.motse.enums.ConvertDirection;
import ink.moshuier.motse.exception.CommonException;
import ink.moshuier.motse.exception.ErrorCode;
import ink.moshuier.motse.repository.MtBaseModelRepository;
import ink.moshuier.motse.util.ProjectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Transactional(
        readOnly = true,
        rollbackFor = {DataAccessException.class})
public class BaseFetcher<BEAN extends BaseBean, ENTITY extends BaseEntity, REPOSITORY extends MtBaseModelRepository<ENTITY>> implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BaseFetcher.applicationContext = applicationContext;
    }

    REPOSITORY repository;
    Class<BEAN> beanClass;
    Class<ENTITY> entityClass;
    Class<REPOSITORY> repositoryClass;

    {
//        Class<?> cls = this.getClass().getSuperclass();
        Type superclass = getClass().getGenericSuperclass();
        if (!(superclass instanceof Class)) {
            ParameterizedType parameterized = (ParameterizedType) superclass;
            beanClass = (Class<BEAN>) parameterized.getActualTypeArguments()[0];
            entityClass = (Class<ENTITY>) parameterized.getActualTypeArguments()[1];
            repositoryClass = (Class<REPOSITORY>) parameterized.getActualTypeArguments()[2];
        }
    }

    public void setRepository() {
        if (repository == null) {
            try {
                repository = ((ApplicationContext) this.getClass().getSuperclass().getDeclaredField("applicationContext").get(this)).getBean(repositoryClass);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(Long id) {
        setRepository();
        repository.deactive(id);
    }

    public void merge(BEAN bean) {
        setRepository();
        ENTITY entity = repository.findOptionalByIdAndActiveStatusIsTrue(bean.getId()).orElseThrow(() -> new CommonException(ErrorCode.INSTANCE_NOT_FOUND));
        ProjectionUtils.mapTo(entity, bean, ConvertDirection.FROM_BEAN_TO_ENTITY);
        repository.saveAndFlush(entity);
    }

    public Long create(BEAN bean) {
        setRepository();
        ENTITY entity = ProjectionUtils.mapTo(entityClass, bean, ConvertDirection.FROM_BEAN_TO_ENTITY);
        entity = repository.saveAndFlush(entity);
        return entity.getId();
    }


    public BEAN getBeanById(Long id) {
        setRepository();
        ENTITY entity = repository.findOptionalByIdAndActiveStatusIsTrue(id).orElseThrow(() -> new CommonException(ErrorCode.INSTANCE_NOT_FOUND));
        BEAN bean = ProjectionUtils.mapTo(beanClass, entity, ConvertDirection.FROM_ENTITY_TO_BEAN);
        return bean;
    }

    public BeanPage<BEAN, ENTITY> search(Pageable pageable) {
        return search(null, pageable);
    }

    public BeanPage<BEAN, ENTITY> search() {
        return search(null, null);
    }

    public BeanPage<BEAN, ENTITY> search(List<SearchCondition> conditions) {
        return search(conditions, null);
    }

    public BeanPage<BEAN, ENTITY> search(List<SearchCondition> conditions, Pageable pageable) {
        setRepository();
        Specification specification = buildSpecification(conditions);
        Page<ENTITY> entities = null;

        if (Objects.isNull(pageable)) {
            pageable = PageRequest.of(0, 1000);
        }
        entities = repository.findAll(specification, pageable);

        BeanPage<BEAN, ENTITY> BeanPage = new BeanPage<BEAN, ENTITY>(entities, beanClass, entityClass);
        return BeanPage;
    }


    Specification<ENTITY> buildSpecification(List<SearchCondition> conditions) {
        Specification<ENTITY> specification =
                (Root<ENTITY> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    if (Objects.nonNull(conditions)) {
                        for (SearchCondition condition : conditions) {
                            Object value = condition.getValue();
                            Path path = root.get(condition.getName());
                            if (value instanceof List && condition.getOperator().equals(SearchCondition.CriteriaOperator.IN)) {
                                CriteriaBuilder.In in = cb.in(path);
                                List values = (List<?>) value;
                                if (Objects.nonNull(values) && values.size() > 0) {
                                    for (Object obj : values) {
                                        in.value(obj);
                                    }
                                    predicates.add(cb.and(in));
                                } else if (!condition.getIgnoreIfNull()) {
                                    Path path_id = root.get("id");
                                    predicates.add(cb.equal(path_id, 0));
                                }
                            } else if (condition.getOperator().equals(SearchCondition.CriteriaOperator.EQUEL)) {
                                if (!(condition.getIgnoreIfNull() && StringUtils.isEmpty(value))) {
                                    predicates.add(cb.equal(path, value));
                                }

                            } else if (condition.getOperator().equals(SearchCondition.CriteriaOperator.LIKE)) {
                                String valueStr = String.valueOf(value);
                                if (!(condition.getIgnoreIfEmpty() && StringUtils.isEmpty(valueStr))) {
                                    predicates.add(cb.like(path, "%" + valueStr + "%"));
                                }
                            }

                        }
                    }

                    Path path_ative_status = root.get("activeStatus");
                    predicates.add(cb.equal(path_ative_status, true));

                    Predicate[] p = new Predicate[predicates.size()];
                    criteriaQuery.where(cb.and(predicates.toArray(p)));
                    criteriaQuery.orderBy(cb.desc(root.get("creationDate")));
                    return predicates.isEmpty() ? cb.conjunction() : criteriaQuery.getRestriction();
                };

        return specification;
    }


}
