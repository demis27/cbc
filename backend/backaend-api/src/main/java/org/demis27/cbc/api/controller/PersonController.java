package org.demis27.cbc.api.controller;

import static org.demis27.cbc.api.controller.ControllerHelper.allows;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.demis27.cbc.api.converter.PersonConverter;
import org.demis27.cbc.api.dto.PersonDTO;
import org.demis27.cbc.api.range.Range;
import org.demis27.cbc.api.range.RangeConverter;
import org.demis27.cbc.api.range.RangeException;
import org.demis27.cbc.api.range.RequestedRangeUnsatisfiableException;
import org.demis27.cbc.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    @Autowired
    private PersonService service;

    @Autowired
    private PersonConverter converter;

    @RequestMapping(path = "/api/person", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public List<PersonDTO> list(HttpServletRequest request, HttpServletResponse response)
            throws InterruptedException, RangeException {
        response.setHeader(HttpHeaders.ACCEPT_RANGES, "resources");
        Range range = getRange(request.getHeader("Range"));
        return converter.convertEntities(service.findPart(range));
    }

    @RequestMapping(path = "/api/person/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public PersonDTO get(@PathVariable(value = "id") String id) {
        return converter.convertEntity(service.findById(id));
    }

    @RequestMapping(path = "/api/person", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public PersonDTO post(@RequestBody @Valid final PersonDTO person) {
        return converter.convertEntity(service.create(converter.convertDTO(person)));
    }

    @RequestMapping(path = "/api/person/{id}", method = RequestMethod.PUT, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public PersonDTO put(@RequestBody @Valid final PersonDTO person, @PathVariable(value = "id") String id) {
        person.id = id;
        return converter.convertEntity(service.update(converter.convertDTO(person)));
    }

    @RequestMapping(path = "/api/person/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") String id) {
        service.delete(id);
    }

    @RequestMapping(path = "/api/person", method = RequestMethod.OPTIONS)
    ResponseEntity<Void> options() {
        return allows(GET, OPTIONS, DELETE, POST, HEAD);
    }

    protected Range getRange(String requestRange) throws RangeException {
        Range range;

        if (requestRange != null) {
            try {
                range = RangeConverter.parse(requestRange);
            } catch (RequestedRangeUnsatisfiableException e) {
                String reason = "Wrong format for the range parameter. The format is: \"resources: page=[page-number];size=[page-size]\" and the parameter value is: " + requestRange;
                throw new RangeException(reason);
            }
        } else {
            range = new Range(0, 10);
        }
        return range;
    }
}
