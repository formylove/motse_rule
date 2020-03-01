package ink.moshuier.motse.api;

import ink.moshuier.motse.api.bean.request.ScoreRequest;
import ink.moshuier.motse.api.bean.response.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Api(value = "习惯/长期任务")
@RestController
@RequestMapping("/score")
public class ScoreController extends RestfulController {


  @ApiOperation(value = "更改分数")
  @PostMapping(value = "/")
  public ResponseBean<Void> rate(@RequestBody ScoreRequest request) {


    return null;
  }





}
