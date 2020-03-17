package ink.moshuier.motse.util;

import ink.moshuier.motse.api.bean.dto.BaseDTO;
import ink.moshuier.motse.bean.SearchCondition;
import ink.moshuier.motse.enums.PersistableEnum;
import ink.moshuier.motse.enums.QuarantsEnum;
import ink.moshuier.motse.enums.TaskType;
import ink.moshuier.motse.enums.UnitDimensionEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

import static ink.moshuier.motse.bean.SearchCondition.DEFAULT_IGNORE_IF_EMPTY;
import static ink.moshuier.motse.bean.SearchCondition.DEFAULT_IGNORE_IF_NULL;

/**
 * @author : Sarah Xu
 * @date : 2020-01-20
 **/
@Aspect
@Component
public class ControllerAdvice {


    @Pointcut("execution(public * ink.moshuier.motse.api.*+.*ByPage(..)))")
    public void pageAspect() {

    }

    @Pointcut("execution(public * ink.moshuier.motse.api.*+.search(..)))")
    public void search() {

    }

    @Pointcut("execution(public * ink.moshuier.motse.api.*+.update(..)))")
    public void update() {

    }

//    @Pointcut("execution(public * ink.moshuier.motse.api.*+.create(..)))")
//    public void create() {
//
//    }
//
//    @Pointcut("create() || update()")
//    public void createOrUpdate() {
//
//    }

    @Around("update()")
    public Object update(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        BaseDTO dto = (BaseDTO) args[0];
        Long id = (Long) args[1];
        dto.setId(id);
        Object result = pjp.proceed(args);
        return result;
    }

    @Around("pageAspect()")
    public Object byPage(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        Integer pageNum = (Integer) args[args.length - 3];
        Integer pageSize = (Integer) args[args.length - 2];
        Pageable pageable = (Pageable) args[args.length - 1];
        PageRequest pageRequest = PageRequest.of(Optional.ofNullable(pageNum).orElse(0), Optional.ofNullable(pageSize).orElse(1000));
        pageable = pageRequest;
        Object result = pjp.proceed(args);
        return result;
    }


    @Around("search()")
    public Object search(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        Map<String, Object> filters = (Map<String, Object>) args[0];
        final List<SearchCondition> searchConditions = new ArrayList<>();
        Map<String, Function<String, PersistableEnum>> enumMap = new HashMap<>();
        enumMap.put("Enum_type", TaskType::getEnumFromName);
        enumMap.put("Enum_unit", UnitDimensionEnum::getEnumFromName);
        enumMap.put("Enum_quarant", QuarantsEnum::getEnumFromName);
        filters.keySet().stream().filter((key) -> !key.startsWith("_null_") && !key.startsWith("_empty_") && !key.startsWith("_enum_") && !key.startsWith("//"))
                .forEach((key) -> {
                    Boolean ignoreIfEmpty = (Boolean) filters.get("_null_" + key);
                    Boolean ignoreIfNull = (Boolean) filters.get("_empty_" + key);
                    String enumType = (String) filters.get("_enum_" + key);
                    SearchCondition.CriteriaOperator operator = SearchCondition.CriteriaOperator.getEnumFromName((String) filters.get("//" + key));
                    Object value = filters.get(key);
                    if (Objects.nonNull(value) && Objects.nonNull(enumType)) {
                        value = enumMap.get(enumType).apply((String) value);
                    }
                    SearchCondition searchCondition = new SearchCondition(key, value, Optional.ofNullable(ignoreIfEmpty).orElse(DEFAULT_IGNORE_IF_EMPTY), Optional.ofNullable(ignoreIfNull).orElse(DEFAULT_IGNORE_IF_NULL), operator);
                    searchConditions.add(searchCondition);
                });

        args[1] = searchConditions;

        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
        }


        Integer pageNum = (Integer) args[args.length - 3];
        Integer pageSize = (Integer) args[args.length - 2];
        Pageable pageable = (Pageable) args[args.length - 1];
        PageRequest pageRequest = PageRequest.of(Optional.ofNullable(pageNum).orElse(0), Optional.ofNullable(pageSize).orElse(1000));
        pageable = pageRequest;
        Object result = pjp.proceed(args);
        return result;
    }


}
