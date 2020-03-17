package ink.moshuier.motse.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Api(value = "用户")
@RestController
@RequestMapping("/users")
public class UserController extends RestfulController {


    @GetMapping("/current")
    public List<String> getMessages(Principal principal) {
        List<String> info = new ArrayList<>();
        info.add(principal.getName());
        // handle request
        return info;
    }
}