package ink.moshuier.motse.api;

import graphql.ExecutionResult;
import ink.moshuier.motse.service.CardService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Api(value = "任务/长期任务")
@RestController
@RequestMapping("/cards")
public class OwlCardController extends RestfulController {
    @Autowired
    CardService cardService;

    @PostMapping("")
    public ResponseEntity<Object> query(@RequestBody String query) {
        ExecutionResult execute = cardService.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }

}
