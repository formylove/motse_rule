package ink.moshuier.motse.api;

import ink.moshuier.motse.annotation.ControllerDefiner;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@ControllerDefiner(value = "用户", path = "/users")
public class UserController extends RestfulController {


    @GetMapping("/current")
    public List<String> getMessages(Principal principal) {
        List<String> info = new ArrayList<>();
        info.add(principal.getName());
        // handle request
        return info;
    }
}