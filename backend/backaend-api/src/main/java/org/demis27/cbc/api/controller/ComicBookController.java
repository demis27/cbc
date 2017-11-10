package org.demis27.cbc.api.controller;

import javax.validation.Valid;

import org.demis27.cbc.api.entity.ComicBookEntity;
import org.demis27.cbc.api.service.ComicBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static org.demis27.cbc.api.controller.ControllerHelper.allows;

@RestController
public class ComicBookController {

    @Autowired
    private ComicBookService service;

    @RequestMapping(path = "/api/comicBook", method = RequestMethod.GET)
    public Flux<ComicBookEntity> list() {
        return service.findAll();
    }

    @RequestMapping(path = "/api/comicBook/{id}", method = RequestMethod.GET)
    public Mono<ComicBookEntity> get(@PathVariable(value = "id") String id) {
        return service.findById(id);
    }

    @RequestMapping(path = "/api/comicBook", method = RequestMethod.POST, produces = APPLICATION_JSON, consumes = APPLICATION_JSON)
    public Mono<ComicBookEntity> post(@RequestBody @Valid final ComicBookEntity comicBook) {
        return service.create(comicBook);
    }

    @RequestMapping(path = "/api/comicBook/{id}", method = RequestMethod.DELETE)
    public Mono<Void> delete(@PathVariable(value = "id") String id) {
        return service.delete(id);
    }

    @RequestMapping(path = "/api/comicBook", method = RequestMethod.OPTIONS)
    ResponseEntity<Void> options() {
        return allows(HttpMethod.GET, HttpMethod.OPTIONS, HttpMethod.DELETE, HttpMethod.POST, HttpMethod.HEAD );
    }
}
