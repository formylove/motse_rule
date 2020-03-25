package ink.moshuier.motse.api;

import ink.moshuier.motse.annotation.ControllerDefiner;
import ink.moshuier.motse.api.bean.dto.UnitDTO;
import ink.moshuier.motse.api.bean.response.ResponseBean;
import ink.moshuier.motse.bean.UnitBean;
import ink.moshuier.motse.enums.DimensionEnum;
import ink.moshuier.motse.service.UnitService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@ControllerDefiner(value = "单位", path = "/units")
public class UnitController extends RestfulController<UnitDTO, UnitBean, UnitService> {

    @GetMapping("/dimensions/")
    public ResponseBean<List<String>> getDimensions() {
        List<String> dimensionNames = Arrays.asList(DimensionEnum.values()).stream().map((dimension) -> dimension.getDbValue()).collect(Collectors.toList());
        return ResponseBean.success(dimensionNames);
    }
}