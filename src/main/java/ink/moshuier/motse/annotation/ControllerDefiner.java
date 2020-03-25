package ink.moshuier.motse.annotation;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
@Api
@RestController
@RequestMapping
public @interface ControllerDefiner {
    String[] path() default {};

    String value() default "";
}