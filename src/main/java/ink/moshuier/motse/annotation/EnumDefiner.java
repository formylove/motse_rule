package ink.moshuier.motse.annotation;


import org.springframework.core.annotation.AliasFor;


public @interface EnumDefiner {
    @AliasFor("value")
    String name() default "";

    @AliasFor("name")
    String value() default "";
}