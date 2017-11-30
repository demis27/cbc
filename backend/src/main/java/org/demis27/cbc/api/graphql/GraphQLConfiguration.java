package org.demis27.cbc.api.graphql;

import static com.coxautodev.graphql.tools.SchemaParser.newParser;

import org.demis27.cbc.api.graphql.resolver.ComicBookDTOResolver;
import org.demis27.cbc.api.graphql.resolver.ComicBookMutation;
import org.demis27.cbc.api.graphql.resolver.ComicBookQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;

@Configuration
public class GraphQLConfiguration {

    @Autowired
    private ComicBookQuery query;

    @Autowired
    private ComicBookMutation mutation;

    @Autowired
    private ComicBookDTOResolver comicBookDTOResolver;

    @Bean
    public GraphQL graphQL() {
        return GraphQL.newGraphQL(graphQLSchema())
                .build();
    }

    @Bean
    public GraphQLSchema graphQLSchema() {
        // @formatter:off
        return newParser()
                .file("schema.graphqls")
                .resolvers(query, mutation, comicBookDTOResolver )
                .build()
                .makeExecutableSchema();
        // @formatter:on
    }
}
