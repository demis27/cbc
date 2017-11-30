package org.demis27.cbc.api.graphql.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.demis27.cbc.api.graphql.resolver.ComicBookQuery;
import org.demis27.cbc.data.entity.ComicBookEntity;
import org.demis27.cbc.service.ComicBookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ReflectionUtils;

import com.jayway.restassured.response.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ComicBookGraphQLControllerTest {

    @LocalServerPort
    protected int port;

    @Mock
    private ComicBookService comicBookService;

    @Autowired
    private ApplicationContext context;

    @Before
    public void setup() {
        final Field serviceField = ReflectionUtils.findField(ComicBookQuery.class, "service");
        ReflectionUtils.makeAccessible(serviceField);
        ReflectionUtils.setField(serviceField, context.getBean("comicBookQuery"), comicBookService);

        List<ComicBookEntity> result = new ArrayList<>();
        ComicBookEntity batman = new ComicBookEntity();
        batman.title = "Batman";
        batman.number = "1";
        result.add(batman);

        when(comicBookService.findAll()).thenReturn(result);

        ComicBookEntity superman = new ComicBookEntity();
        superman.title = "Superman";
        superman.number = "1";

        when(comicBookService.create(any())).thenReturn(superman);
    }


    @Test
    public void allComicBooks_query() throws Exception {
        // @formatter:off
        Response response =
                given()
                        .port(port)
                        .contentType("application/json")
                        .body("{ \"query\": \"{allComicBooks{title number}}\" }")
                        .expect()
                        .statusCode(200)
                        .body("allComicBooks[0].title", equalTo("Batman"), null)
                        .body("allComicBooks[0].number", equalTo("1"), null)
                .when()
                        .post("/graphql/comicBook");
        // @formatter:on
    }

    @Test
    public void createComicBook_mutation() throws Exception {
        // @formatter:off
        Response response =
                given()
                        .port(port)
                        .contentType("application/json")
                        .body("{ \"query\": \"mutation createComicBook {createComicBook(title:\\\"Superman\\\",number:\\\"1\\\") {title number}}\" }")
                        .expect()
                        .statusCode(200)
                        .body("createComicBook.title", equalTo("Superman"), null)
                        .body("createComicBook.number", equalTo("1"), null)
                .when()
                        .post("/graphql/comicBook");
        // @formatter:on
    }
}