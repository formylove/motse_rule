package ink.moshuier.motse.service.fetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import ink.moshuier.motse.dao.CardDao;
import ink.moshuier.motse.dao.bean.CardBean;
import ink.moshuier.motse.model.entity.CardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author : Sarah Xu
 * @date : 2020-01-30
 **/
@Component
public class CardDataFetcher extends BaseFetcher<CardBean, CardEntity, CardDao> implements DataFetcher {
    @Autowired
    CardDao cardDao;

    @Override
    public Object get(DataFetchingEnvironment dataFetchingEnvironment) {
        Long id = ((Integer) dataFetchingEnvironment.getArgument("id")).longValue();
        CardBean cardBean = CardBean.builder().cname(Arrays.asList("柯尔鸭")).ename(Arrays.asList("Call Duck")).id(23L).desc("wocaowuqing").portraits(Arrays.asList("sr443asf34q3.jpg")).build();
//        return getBeanById(id);
        return cardBean;
    }
}
