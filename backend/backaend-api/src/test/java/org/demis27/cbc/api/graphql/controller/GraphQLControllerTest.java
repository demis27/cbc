package org.demis27.cbc.api.graphql.controller;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jayway.restassured.response.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GraphQLControllerTest {

    @LocalServerPort
    protected int port;

    @Test
    public void getListComicBook() throws Exception {
        // @formatter:off
        Response response =
                given()
                        .port(port)
                        .contentType("application/json")
                        .body("{ \"query\": \"{allComicBooks{title number}}\" }")
                        .expect()
                        .statusCode(200)
                .when()
                        .post("/graphql");
        // @formatter:on

    }
}