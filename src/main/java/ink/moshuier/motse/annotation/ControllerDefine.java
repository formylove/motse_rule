package ink.moshuier.motse.annotation;


import io.swagger.annotations.Api;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
@Api(value = "任务/长期任务")
@RestController
@RequestMapping
public @interface ControllerDefine {
    @AliasFor("path")
    String[] path() default {};

}