package ink.moshuier.motse.annotation;


import ink.moshuier.motse.enums.ConvertDirection;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Repeatable(ProjectionContainer.class)
@Inherited
public @interface Projection {
    String DEFAULT_DEST = "FROM_FIELD_NAME";
    String DEFAULT_SRC = "FROM_FIELD_NAME";

    ConvertDirection direction() default ConvertDirection.ALL_DIRECTION;

    String dest() default DEFAULT_DEST;

    String src() default DEFAULT_SRC;

    boolean ignoreIfNull() default false;

    //不做投影处理
    boolean ignore() default false;

    boolean ignoreIfBlank() default false;

}