package ink.moshuier.motse.api;

import ink.moshuier.motse.annotation.ControllerDefiner;
import ink.moshuier.motse.api.bean.dto.GenreDTO;
import ink.moshuier.motse.bean.GenreBean;
import ink.moshuier.motse.service.GenreService;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@ControllerDefiner(value = "分类", path = "/genres")
public class GenreController extends RestfulController<GenreDTO, GenreBean, GenreService> {

}