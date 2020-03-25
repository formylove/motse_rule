package ink.moshuier.motse.api;

import ink.moshuier.motse.annotation.ControllerDefiner;
import ink.moshuier.motse.api.bean.request.ScoreRequest;
import ink.moshuier.motse.api.bean.response.ResponseBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@ControllerDefiner(value = "打分", path = "/score")
public class ScoreController extends RestfulController {


  @ApiOperation(value = "更改分数")
  @PostMapping(value = "/")
  public ResponseBean<Void> rate(@RequestBody ScoreRequest request) {


    return null;
  }





}
