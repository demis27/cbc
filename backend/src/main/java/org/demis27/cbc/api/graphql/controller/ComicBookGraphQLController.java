package org.demis27.cbc.api.graphql.controller;

import java.util.Map;

import org.demis27.cbc.service.GraphQLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ComicBookGraphQLController {

    @Autowired
    private GraphQLService graphQLService;

    @RequestMapping(value = "/graphql/comicBook",method = RequestMethod.POST)
    public Object handle(@RequestBody Map<String,String> query) {
        return graphQLService.resolve(query);
    }

}
