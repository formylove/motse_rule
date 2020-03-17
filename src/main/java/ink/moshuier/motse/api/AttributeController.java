package ink.moshuier.motse.api;

import ink.moshuier.motse.api.bean.dto.TaskDTO;
import ink.moshuier.motse.api.bean.response.ResponseBean;
import ink.moshuier.motse.bean.TaskBean;
import ink.moshuier.motse.enums.UnitDimensionEnum;
import io.swagger.annotations.Api;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Api(value = "单位")
@RestController
@RequestMapping("/attributes")
public class AttributeController extends RestfulController implements BaseApi<TaskDTO, TaskBean> {
    public ResponseBean<Map<String, Object>> create(@AuthenticationPrincipal OidcUser oidcUser, TaskDTO request) {
        return null;
    }

    @GetMapping("/dimensions")
    public ResponseBean<List<String>> getDimensions() {
        List<String> dimensionNames = Arrays.asList(UnitDimensionEnum.values()).stream().map((dimension) -> dimension.getDbValue()).collect(Collectors.toList());
        return ResponseBean.success(dimensionNames);
    }
}