package ink.moshuier.motse.service;

import ink.moshuier.motse.bean.GenreBean;
import ink.moshuier.motse.entity.GenreEntity;
import ink.moshuier.motse.repository.GenreRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Service
@Transactional
public class GenreService extends BaseDao<GenreBean, GenreEntity, GenreRepository> {


}

