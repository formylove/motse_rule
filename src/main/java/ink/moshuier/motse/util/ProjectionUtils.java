package ink.moshuier.motse.util;

import com.google.common.base.Strings;
import ink.moshuier.motse.annotation.Projection;
import ink.moshuier.motse.annotation.ProjectionContainer;
import ink.moshuier.motse.api.bean.dto.BaseDTO;
import ink.moshuier.motse.bean.BaseBean;
import ink.moshuier.motse.entity.BaseEntity;
import ink.moshuier.motse.enums.ConvertDirection;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : Sarah Xu
 * @date : 2019-12-30
 **/
public class ProjectionUtils<T> {
    static final Projection DEFAULT_PROJECTION = new
            Projection() {
                @Override
                public Class<? extends Annotation> annotationType() {
                    return Projection.class;
                }

                @Override
                public ConvertDirection direction() {
                    return ConvertDirection.FROM_BEAN_TO_ENTITY;
                }

                @Override
                public String dest() {
                    return Projection.DEFAULT_DEST;
                }

                @Override
                public String src() {
                    return Projection.DEFAULT_SRC;
                }

                @Override
                public boolean ignoreIfNull() {
                    return false;
                }

                @Override
                public boolean ignore() {
                    return false;
                }

                @Override
                public boolean ignoreIfBlank() {
                    return false;
                }

            };


    public static <T> T mapFromBeanToResponse(Class<T> destClazz, Object srcObject) {
        return mapTo(destClazz, srcObject, ConvertDirection.FROM_BEAN_TO_RESPONSE);
    }

    public static <T> T mapFromBeanToEntity(Class<T> destClazz, Object srcObject) {
        return mapTo(destClazz, srcObject, ConvertDirection.FROM_BEAN_TO_ENTITY);
    }

    //指定project注解进行映射
    public static <T> T mapTo(Class<T> destClazz, Object srcObject, ConvertDirection direction) {
        if (Objects.isNull(srcObject)) {
            return null;
        }
        if (Objects.isNull(direction)) {
            return mapTo(destClazz, srcObject);
        }
        Object destObject = null;
        try {
            //目标类必须有无参构造函数
            destObject = destClazz.newInstance();
            for (Field srcField : getAllFields(srcObject.getClass())) {
                Projection projection = DEFAULT_PROJECTION;
                ProjectionContainer projectionContainer = srcField.getAnnotation(ProjectionContainer.class);
                if (Objects.nonNull(projectionContainer)) {
                    Projection[] projections = projectionContainer.value();
                    for (Projection p : projections) {
                        if (p.direction().equals(direction)) {
                            projection = p;
                        }
                    }
                } else if (srcField.isAnnotationPresent(Projection.class)) {
                    Projection projectionTmp = srcField.getDeclaredAnnotation(Projection.class);
                    if (projectionTmp.direction().equals(direction)) {
                        projection = projectionTmp;
                    }
                }
                setTheValueForField(srcObject, destObject, srcField, projection);
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        return (T) destObject;
    }

    //新建一个目标bean
    public static <T> T mapTo(Class<T> destClazz, Object srcObject) {
        if (Objects.isNull(srcObject)) {
            return null;
        }
        Object destObject = null;
        try {
            //目标类必须有无参构造函数
            destObject = destClazz.newInstance();
            for (Field srcField : getAllFields(srcObject.getClass())) {
                Projection projection = DEFAULT_PROJECTION;
                ProjectionContainer projectionContainer = srcField.getAnnotation(ProjectionContainer.class);
                if (Objects.nonNull(projectionContainer)) {
                    Projection[] projections = projectionContainer.value();
                    projection = projections[0];
                } else if (srcField.isAnnotationPresent(Projection.class) && srcField.getDeclaredAnnotation(Projection.class).direction().equals(ConvertDirection.ALL_DIRECTION)) {
                    projection = srcField.getDeclaredAnnotation(Projection.class);
                }
                setTheValueForField(srcObject, destObject, srcField, projection);
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        return (T) destObject;
    }


    //merge
    public static Object mapTo(Object destObject, Object srcObject, ConvertDirection direction) {
        if (Objects.isNull(srcObject)) {
            return null;
        }
        try {
            //目标类必须有无参构造函数
            for (Field srcField : getAllFields(srcObject.getClass())) {
                Projection projection = DEFAULT_PROJECTION;
                ProjectionContainer projectionContainer = srcField.getAnnotation(ProjectionContainer.class);
                if (Objects.nonNull(projectionContainer)) {
                    Projection[] projections = projectionContainer.value();
                    projection = projections[0];
                    if (!Objects.isNull(projections) && Objects.nonNull(direction)) {
                        for (Projection p : projections) {
                            if (p.direction().equals(direction)) {
                                projection = p;
                            }
                        }
                    }
                } else if (srcField.isAnnotationPresent(Projection.class)) {
                    Projection projectionTmp = srcField.getDeclaredAnnotation(Projection.class);
                    if (projectionTmp.direction().equals(direction)) {
                        projection = projectionTmp;
                    }
                }
                setTheValueForField(srcObject, destObject, srcField, projection);
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return destObject;
    }


    static void setTheValueForField(Object srcObject, Object destObject, Field srcField, Projection projection) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> srcClass = srcObject.getClass();
        //使可以获取private成员变量数据
        srcField.setAccessible(true);
        Object srcFieldValue = null;
        //默认投影到同名字段
        String destFieldName = projection != null && projection.dest().equals(Projection.DEFAULT_DEST) ? srcField.getName() : projection.dest();
        Field destField = null;
        Class<?> destClass = destObject.getClass();
        while (destClass != null) {
            try {
                destField = destClass.getDeclaredField(destFieldName);
                destField.setAccessible(true);
                break;
            } catch (NoSuchFieldException e) {
                destClass = destClass.getSuperclass();
            }
        }
        if (projection.ignore() || Objects.isNull(destField)) {
            return;
        }
        //默认从字段取值
        if (projection.src().equals(Projection.DEFAULT_SRC)) {
            srcFieldValue = getSrcValueFromField(srcObject, srcField, destField, projection.direction());
        } else {
            //否则从get方法取值。get方法别加参数
            Method getMethod = srcClass.getMethod(projection.src());
            srcFieldValue = getMethod.invoke(srcObject);
        }
        //如果在值为null或者empty时候选择是否还继续为destField赋值(针对merge对象的情况，防止null覆盖数据库已有的值)
        if (!(projection.ignoreIfNull() && Objects.isNull(srcFieldValue)) && !(srcFieldValue instanceof String && projection.ignoreIfBlank() && Strings.nullToEmpty(String.valueOf(srcFieldValue)).trim().isEmpty())) {
            destField.set(destObject, srcFieldValue);
        }
    }

    //从src字段获取值
    public static Object getSrcValueFromField(Object srcObject, Field srcField, Field destField, ConvertDirection convertDirection) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object srcFieldValue = srcField.get(srcObject);
        Class<?> srcFieldType = srcField.getType();
        Class<?> destFieldType = destField.getType();

        if (BaseDTO.class.isAssignableFrom(srcFieldType) || BaseBean.class.isAssignableFrom(srcFieldType) || BaseEntity.class.isAssignableFrom(srcFieldType)) {
            Object result = ProjectionUtils.mapTo(destFieldType, srcFieldValue, convertDirection);
            return result;
        }


        if (srcFieldType.isEnum() && destFieldType == String.class) {
            Method nameMethod = srcFieldType.getMethod("name");
            srcFieldValue = nameMethod.invoke(srcFieldValue);
        } else if (destFieldType.isEnum() && srcFieldType == String.class) {
            Method getEnumFromValue = destFieldType.getMethod("getEnumFromValue", String.class);
            srcFieldValue = getEnumFromValue.invoke(null, srcFieldValue);
        } else if (destFieldType == List.class && srcFieldType == List.class) {
            Type genericDestType = destField.getGenericType();
            Type genericSrcType = srcField.getGenericType();
            if (genericDestType instanceof ParameterizedType) {
                ParameterizedType destPt = (ParameterizedType) genericDestType;
                //得到泛型里的class类型对象
                if (!(destPt.getActualTypeArguments()[0] instanceof ParameterizedType)) {
                    Class<?> genericDestClazz = (Class<?>) destPt.getActualTypeArguments()[0];
                    if (genericDestClazz.isEnum()) {
                        List<String> srcValueList = (List<String>) srcFieldValue;
                        if (Objects.nonNull(srcValueList)) {
                            List enumList = new ArrayList<>();
                            for (String enumStr : srcValueList) {
                                Method getEnumFromValue = genericDestClazz.getMethod("getEnumFromValue", String.class);
                                enumList.add(getEnumFromValue.invoke(null, enumStr));
                            }
                            srcFieldValue = enumList;
                        }
                    } else if (BaseDTO.class.isAssignableFrom(genericDestClazz) || BaseBean.class.isAssignableFrom(genericDestClazz) || BaseEntity.class.isAssignableFrom(genericDestClazz)) {
                        List srcValueList = (List) srcFieldValue;
                        if (Objects.nonNull(srcValueList)) {
                            srcFieldValue = srcValueList.stream().map((e) -> ProjectionUtils.mapTo(genericDestClazz, e, convertDirection)).collect(Collectors.toList());
                        }
                    }
                }
            }
        }
        return srcFieldValue;
    }

    public static List<Field> getAllFields(Class cls) {
        List<Field> fieldList = new ArrayList<>();
        while (cls != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(cls.getDeclaredFields())));
            cls = cls.getSuperclass();
        }
        return fieldList;
    }

    public static <T> List<T> mapList(Class<T> clazz, List objects, ConvertDirection direction) {
        List<T> results = (List<T>) objects.stream().map((e) -> ProjectionUtils.mapTo(clazz, e, direction)).collect(Collectors.toList());
        return results;
    }

    public static <T> List<T> mapList(Class<T> clazz, List objects) {
        List<T> results = (List<T>) objects.stream().map((e) -> ProjectionUtils.mapTo(clazz, e)).collect(Collectors.toList());
        return results;
    }
}