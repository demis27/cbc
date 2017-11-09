package org.demis27.cbc.api.controller;

import static com.jayway.restassured.RestAssured.given;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.demis27.cbc.api.dto.Person;
import org.demis27.cbc.api.service.PersonService;
import org.junit.Assert;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

    @Mock
    private PersonService service;

    @LocalServerPort
    protected int port;

    @Autowired
    private ApplicationContext context;

    @Test
    public void testList() throws Exception {
        final Field serviceField = ReflectionUtils.findField(PersonController.class, "service");
        ReflectionUtils.makeAccessible(serviceField);
        ReflectionUtils.setField(serviceField, context.getBean("personController"), service);

        Person stan = new Person();
        stan.firstName = "Stan";
        stan.lastName = "Lee";
        stan.id = "1";

        Flux<Person> result = Flux.just(stan);
        when(service.findAll()).thenReturn(result);

        Response response = given().port(port).expect().statusCode(200).when().get("/api/person");
        Person[] persons = response.getBody().as(Person[].class);
        Assert.assertEquals(1, persons.length);
    }

    @Test
    public void testGet() throws Exception {
    }

    @Test
    public void testPost() throws Exception {
    }

    @Test
    public void testDelete() throws Exception {
    }

    @Test
    public void testOptions() throws Exception {
        Response response = given().port(port).expect().statusCode(204).when().options("/api/person");

        //Assert.assertEquals("HEAD,GET,POST,OPTIONS,DELETE", response.header("Allow"));
    }

}