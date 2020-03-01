package ink.moshuier.motse.fetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import ink.moshuier.motse.bean.CardBean;
import ink.moshuier.motse.entity.CardEntity;
import ink.moshuier.motse.repository.CardRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author : Sarah Xu
 * @date : 2020-01-30
 **/
@Component
public class CardDataFetcher extends BaseFetcher<CardBean, CardEntity, CardRepository> implements DataFetcher {

    @Override
    public Object get(DataFetchingEnvironment dataFetchingEnvironment) {
        Long id = ((Integer) dataFetchingEnvironment.getArgument("id")).longValue();
        CardBean cardBean = CardBean.builder().cname(Arrays.asList("柯尔鸭")).ename(Arrays.asList("Call Duck")).id(23L).desc("wocaowuqing").portraits(Arrays.asList("sr443asf34q3.jpg")).build();
//        return getBeanById(id);
        return cardBean;
    }
}
