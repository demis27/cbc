package org.demis27.cbc.api.controller;

import javax.validation.Valid;

import org.demis27.cbc.api.converter.PersonConverter;
import org.demis27.cbc.api.dto.PersonDTO;
import org.demis27.cbc.api.entity.PersonEntity;
import org.demis27.cbc.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static org.demis27.cbc.api.controller.ControllerHelper.allows;

@RestController
public class PersonController {

    @Autowired
    private PersonService service;

    @Autowired
    private PersonConverter converter;

    @RequestMapping(path = "/api/person", method = RequestMethod.GET)
    public Flux<PersonDTO> list() {
        return converter.convertEntities(service.findAll());
    }

    @RequestMapping(path = "/api/person/{id}", method = RequestMethod.GET)
    public Mono<PersonDTO> get(@PathVariable(value = "id") String id) {
        return converter.convertEntity(service.findById(id));
    }

    @RequestMapping(path = "/api/person", method = RequestMethod.POST, produces = APPLICATION_JSON, consumes = APPLICATION_JSON)
    public Mono<PersonDTO> post(@RequestBody @Valid final PersonDTO person) {
        return converter.convertEntity(service.create(converter.convertDTO(person)));
    }

    @RequestMapping(path = "/api/person/{id}", method = RequestMethod.DELETE)
    public Mono<Void> delete(@PathVariable(value = "id") String id) {
        return service.delete(id);
    }

    @RequestMapping(path = "/api/person", method = RequestMethod.OPTIONS)
    ResponseEntity<Void> options() {
        return allows(HttpMethod.GET, HttpMethod.OPTIONS, HttpMethod.DELETE, HttpMethod.POST, HttpMethod.HEAD);
    }
}
