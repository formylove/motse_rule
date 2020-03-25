package ink.moshuier.motse.api;

import ink.moshuier.motse.annotation.ControllerDefiner;
import ink.moshuier.motse.api.bean.dto.AttributeDTO;
import ink.moshuier.motse.bean.AttributeBean;
import ink.moshuier.motse.service.AttributeService;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@ControllerDefiner(value = "属性", path = "/attributes")
public class AttributeController extends RestfulController<AttributeDTO, AttributeBean, AttributeService> {


}