package ink.moshuier.motse.fetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import ink.moshuier.motse.bean.CardBean;
import ink.moshuier.motse.entity.CardEntity;
import ink.moshuier.motse.repository.CardRepository;
import org.springframework.stereotype.Component;

/**
 * @author : Sarah Xu
 * @date : 2020-01-30
 **/
@Component
public class CardsDataFetcher extends BaseFetcher<CardBean, CardEntity, CardRepository> implements DataFetcher {

    @Override
    public Object get(DataFetchingEnvironment dataFetchingEnvironment) {
        return search();
    }
}
