package ink.moshuier.motse.service;

import ink.moshuier.motse.bean.AttributeBean;
import ink.moshuier.motse.entity.AttributeEntity;
import ink.moshuier.motse.repository.AttributeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Service
@Transactional
public class AttributeService extends BaseDao<AttributeBean, AttributeEntity, AttributeRepository> {


}

