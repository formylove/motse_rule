package ink.moshuier.motse.api.impl;

import ink.moshuier.motse.api.ScoreApi;
import ink.moshuier.motse.api.bean.ResponseBean;
import org.springframework.stereotype.Controller;

/**
 * @author : Sarah Xu
 * @date : 2019-07-12
 **/
@Controller
public class ScoreApiImpl implements ScoreApi {
    @Override
    public ResponseBean<Void> rate(ScoreRequest request) {


        return null;
    }
}
