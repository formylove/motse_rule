package ink.moshuier.motse.service.fetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import ink.moshuier.motse.dao.CardDao;
import ink.moshuier.motse.dao.bean.CardBean;
import ink.moshuier.motse.model.entity.CardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author : Sarah Xu
 * @date : 2020-01-30
 **/
@Component
public class UpdateCardDataFetcher extends BaseFetcher<CardBean, CardEntity, CardDao> implements DataFetcher {
    @Autowired
    CardDao cardDao;

    @Override
    public Object get(DataFetchingEnvironment dataFetchingEnvironment) {
        Long id = ((Integer) dataFetchingEnvironment.getArgument("id")).longValue();
        Map input = (Map) dataFetchingEnvironment.getArgument("input");
        List<String> cname = (List<String>) input.get("cname");
        List<String> ename = (List<String>) input.get("ename");
        List<String> portraits = (List<String>) input.get("portraits");
        String desc = (String) input.get("desc");
        return null;
    }
}
