package ink.moshuier.motse.annotation;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Inherited
public @interface ProjectionContainer {
    Projection[] value() default {};
}
