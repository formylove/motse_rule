package ink.moshuier.motse.fetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import ink.moshuier.motse.bean.CardBean;
import ink.moshuier.motse.entity.CardEntity;
import ink.moshuier.motse.repository.CardRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author : Sarah Xu
 * @date : 2020-01-30
 **/
@Component
public class UpdateCardDataFetcher extends BaseFetcher<CardBean, CardEntity, CardRepository> implements DataFetcher {

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
