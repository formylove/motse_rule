package ink.moshuier.motse.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Api(value = "单位")
@RestController
@RequestMapping("/tasks")
public class UnitController extends RestfulController {
}