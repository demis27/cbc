package org.demis27.cbc.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @RequestMapping(path = "/person", method = RequestMethod.GET)
    public Flux<Person> list() {
        return repository.findAll();
    }

}
