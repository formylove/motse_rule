package ink.moshuier.motse.service.fetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import ink.moshuier.motse.dao.CardDao;
import ink.moshuier.motse.dao.bean.CardBean;
import ink.moshuier.motse.model.entity.CardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : Sarah Xu
 * @date : 2020-01-30
 **/
@Component
public class CardsDataFetcher extends BaseFetcher<CardBean, CardEntity, CardDao> implements DataFetcher {
    @Autowired
    CardDao cardDao;

    @Override
    public Object get(DataFetchingEnvironment dataFetchingEnvironment) {
        return search();
    }
}
