package ink.moshuier.motse.api.bean.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ink.moshuier.motse.api.bean.request.BaseRequest;
import lombok.Data;

/**
 * @author : Sarah Xu
 * @date : 2020-02-26
 **/
@Data
public class BaseDTO implements BaseRequest {
    @JsonProperty        //必须加上，默认是不会序列化父类字段的
            Long id;
}
