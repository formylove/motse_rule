package ink.moshuier.motse.api.bean.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Data
@ResponseBody
@NoArgsConstructor
public class ResponseBean<E> {
  Integer errCode = 0;
  @JsonRawValue
  String errMsg = "\"OK\"";

  @JsonInclude(JsonInclude.Include.NON_NULL)
  Integer pageNumber;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  Integer pageSize;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  Integer totalPages;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  Long totalCount;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  E data = null;

  public ResponseBean(E data) {
    this.data = data;
  }

  public ResponseBean(Integer errCode, String errMsg) {
    this.errCode = errCode;
    this.errMsg = errMsg;
  }

  public static ResponseBean builder() {
      ResponseBean<Map<String, Object>> mapResponseBean = new ResponseBean<Map<String, Object>>();
      mapResponseBean.data = new HashMap<>();
      return mapResponseBean;
  }

    public static ResponseBean success() {
        ResponseBean<Void> voidResponseBean = new ResponseBean<>();
        return voidResponseBean;
    }

    public static ResponseBean success(String key, Object value) {
        ResponseBean<Map<String, Object>> responseBean = new ResponseBean<>();
        responseBean.data = new HashMap<String, Object>();
        return responseBean.populate(key, value);
    }

    public static <V> ResponseBean<V> success(V t) {
        ResponseBean<V> typeResponseBean = new ResponseBean<>(t);
        return typeResponseBean;
    }


    public static ResponseBean id(Long id) {
        ResponseBean<Map<String, Object>> responseBean = new ResponseBean<>();
        responseBean.data = new HashMap<String, Object>();
        return responseBean.populate("id", id);
    }


    public ResponseBean populate(String key, Object value) {
        ((Map<String, Object>) this.data).put(key, value);
        return this;
    }
}
