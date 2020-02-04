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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;

/**
 * @author : Sarah Xu
 * @date : 2019-05-28
 */
@Service
@Transactional
public class CardService extends GraphQLService {
    @Value("classpath*:card.graphql")
    private Resource resource;
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
        File schemaFile = resource.getFile();
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
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

