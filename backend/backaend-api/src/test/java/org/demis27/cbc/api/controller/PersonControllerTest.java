package org.demis27.cbc.api.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.demis27.cbc.api.dto.PersonDTO;
import org.demis27.cbc.api.entity.PersonEntity;
import org.demis27.cbc.api.service.PersonService;
import org.junit.Assert;
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

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

    @Mock
    private PersonService service;

    @LocalServerPort
    protected int port;

    @Autowired
    private ApplicationContext context;

    @Before
    public void setup() {
        final Field serviceField = ReflectionUtils.findField(PersonController.class, "service");
        ReflectionUtils.makeAccessible(serviceField);
        ReflectionUtils.setField(serviceField, context.getBean("personController"), service);

        PersonEntity stan = new PersonEntity();
        stan.firstName = "Stan";
        stan.lastName = "Lee";
        stan.id = "1";

        PersonEntity jack = new PersonEntity();
        jack.firstName = "Jack";
        jack.lastName = "Kirby";
        jack.id = "2";

        PersonEntity steve = new PersonEntity();
        steve.firstName = "Steve";
        steve.lastName = "Ditko";
        steve.id = "3";

        when(service.findAll()).thenReturn(Flux.just(stan, jack));
        when(service.findById("1")).thenReturn(Mono.just(stan));
        when(service.create(any(PersonEntity.class))).thenReturn(Mono.just(steve));
        when(service.delete(anyString())).thenReturn(Mono.<Void>empty());
    }

    @Test
    public void testList() throws Exception {
        // @formatter:off
        Response response = 
                given()
                        .port(port)
                        .expect()
                        .statusCode(200)
                .when()
                        .get("/api/person");
        // @formatter:on
        PersonDTO[] persons = response.getBody().as(PersonDTO[].class);
        Assert.assertEquals(2, persons.length);
    }

    @Test
    public void testGet() throws Exception {
        // @formatter:off
        Response response =
                given()
                        .port(port)
                        .expect()
                        .statusCode(200)
                .when()
                        .get("/api/person/1");
        // @formatter:on
        PersonDTO person = response.as(PersonDTO.class);
        Assert.assertEquals("Stan", person.firstName);
        Assert.assertEquals("Lee", person.lastName);
        Assert.assertEquals("1", person.id);
    }

    @Test
    public void testPost() throws Exception {
        String steve = "{\"firstName\": \"Steve\", \"lastName\": \"Ditko\"}";
        // @formatter:off
        Response response =
                given()
                        .port(port)
                        .header("Content-Type", "application/json")
                        .body(steve)
                        .expect()
                        .statusCode(200)
                .when()
                        .post("/api/person");
        // @formatter:on

        PersonDTO person = response.as(PersonDTO.class);
        Assert.assertEquals("Steve", person.firstName);
        Assert.assertEquals("Ditko", person.lastName);
        Assert.assertEquals("3", person.id);
    }

    @Test
    public void testDelete() throws Exception {
        // @formatter:off
        Response response =
                given()
                        .port(port)
                        .expect()
                        .statusCode(200)
                .when()
                        .delete("/api/person/1");
        // @formatter:on
    }

    @Test
    public void testOptions() throws Exception {
        // @formatter:off
        Response response =
                given()
                        .port(port)
                        .expect()
                        .statusCode(204)
                .when()
                        .options("/api/person");
        // @formatter:on
        assertMethods("OPTIONS,GET,POST,HEAD,DELETE", response.header("Allow"));
    }

    public void assertMethods(String expected, String actual) {
        List<String> actualMethods = Arrays.asList(actual.split("\\,", -1)).stream().sorted().collect(Collectors.toList());
        List<String> expectedMethods = Arrays.asList(expected.split("\\,", -1)).stream().sorted().collect(Collectors.toList());

        Assert.assertEquals(expectedMethods, actualMethods);
    }
}