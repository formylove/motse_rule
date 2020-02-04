package ink.moshuier.motse.service;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import ink.moshuier.motse.service.fetcher.CardDataFetcher;
import ink.moshuier.motse.service.fetcher.CardsDataFetcher;
import ink.moshuier.motse.service.fetcher.CreateCardDataFetcher;
import ink.moshuier.motse.service.fetcher.UpdateCardDataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Service
@Transactional
public class CardService extends GraphQLService {
    @Autowired
    private ResourceLoader resourceLoader;
    //    @Value("classpath:card.graphql") 打成jar包后无法通过此方法获取jar包中的文件
//    private Resource resource;
    private GraphQL graphQL;
    @Autowired
    private CardsDataFetcher cardsFetcher;
    @Autowired
    private CardDataFetcher cardFetcher;
    @Autowired
    private UpdateCardDataFetcher updateCardDataFetcher;
    @Autowired
    private CreateCardDataFetcher createCardDataFetcher;

    @PostConstruct
    public void loadSchema() throws IOException {
        //https://smarterco.de/java-load-file-from-classpath-in-spring-boot/
//        In the spring jar package, I use new ClassPathResource(filename).getFile(), which throws theexception:
//        cannot be resolved to absolute file path because it does not reside in the file system: jar
//        But using new ClassPathResource(filename).getInputStream() will solve this problem. The reason is that the configuration file in the jar does not exist in the operating system's file tree,so must use getInputStream().
        InputStream inputStream = resourceLoader.getResource("classpath:card.graphql").getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(inputStreamReader);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();


    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring().type("Mutation", typeWiring -> typeWiring.
                dataFetcher("create", createCardDataFetcher).
                dataFetcher("update", updateCardDataFetcher)).
                type("Query", typeWiring ->
                        typeWiring.dataFetcher("cards", cardsFetcher).
                                dataFetcher("card", cardFetcher)).build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}

