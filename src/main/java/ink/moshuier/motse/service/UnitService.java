package ink.moshuier.motse.service;

import ink.moshuier.motse.bean.UnitBean;
import ink.moshuier.motse.entity.UnitEntity;
import ink.moshuier.motse.repository.UnitRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Service
@Transactional
public class UnitService extends BaseDao<UnitBean, UnitEntity, UnitRepository> {


}

