package ink.moshuier.motse.api.bean.response;

import lombok.Builder;
import lombok.Data;

import java.util.Base64;

/**
 * @author : Sarah Xu
 * @date : 2019-05-22
 **/
@Data
public class ResourceResponse {
    private Long resourceId;
    private String mimeType;
    private String resourceBase64;

@Builder
    public ResourceResponse(Long resourceId, String mimeType, byte[] resourceDataBytes) {
        this.resourceId = resourceId;
        this.resourceBase64 = new String(Base64.getEncoder().encode(resourceDataBytes));
        this.mimeType=mimeType;
    }
}
