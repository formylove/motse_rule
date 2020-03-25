package ink.moshuier.motse.api;

import graphql.ExecutionResult;
import ink.moshuier.motse.annotation.ControllerDefiner;
import ink.moshuier.motse.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@ControllerDefiner(value = "知识卡片", path = "/cards")
public class OwlCardController {
    @Autowired
    CardService cardService;

    @PostMapping("")
    public ResponseEntity<Object> query(@RequestBody String query) {
        ExecutionResult execute = cardService.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }

}
