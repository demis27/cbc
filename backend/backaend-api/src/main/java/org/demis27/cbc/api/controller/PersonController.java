package org.demis27.cbc.api.controller;

import static org.demis27.cbc.api.controller.ControllerHelper.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.demis27.cbc.api.converter.PersonConverter;
import org.demis27.cbc.api.dto.PersonDTO;
import org.demis27.cbc.api.entity.PersonEntity;
import org.demis27.cbc.api.range.Range;
import org.demis27.cbc.api.range.RangeException;
import org.demis27.cbc.api.service.PersonService;
import org.demis27.cbc.api.sort.SortParameterElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    @Autowired
    private PersonService service;

    @Autowired
    private PersonConverter converter;

    // ------------------------------------------------------------------------
    // GET
    // ------------------------------------------------------------------------
    @RequestMapping(path = "/api/person", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public List<PersonDTO> list(@RequestParam(value = "sort", required = false) String sortParameters, HttpServletRequest request,
            HttpServletResponse response) throws InterruptedException, RangeException {
        response.setHeader(HttpHeaders.ACCEPT_RANGES, "resources");

        Range range = getRange(request.getHeader("Range"));
        List<SortParameterElement> sorts = getSorts(sortParameters);

        List<PersonDTO> persons = converter.convertEntities(service.findPart(range, sorts));
        if (persons == null || persons.size() == 0) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
        } else {
            response.setHeader(HttpHeaders.CONTENT_RANGE,
                    "resources " + range.getStart() + "-" + Math.min(range.getEnd(), persons.size()) + "/*");
            response.setStatus(HttpStatus.OK.value());
        }

        return persons;
    }

    @RequestMapping(path = "/api/person/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public PersonDTO get(@PathVariable(value = "id") String id, HttpServletResponse response) {
        PersonDTO person = converter.convertEntity(service.findById(id));
        if (person != null) {
            response.setStatus(HttpStatus.OK.value());
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return person;
    }

    // ------------------------------------------------------------------------
    // POST
    // ------------------------------------------------------------------------
    @RequestMapping(path = "/api/person", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public PersonDTO post(@RequestBody @Valid final PersonDTO dto, HttpServletResponse response) {
        PersonDTO person = converter.convertEntity(service.create(converter.convertDTO(dto)));
        response.setStatus(HttpStatus.CREATED.value());
        return person;
    }

    // ------------------------------------------------------------------------
    // PUT
    // ------------------------------------------------------------------------
    @RequestMapping(path = "/api/person/{id}", method = RequestMethod.PUT, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public PersonDTO put(@RequestBody @Valid final PersonDTO person, @PathVariable(value = "id") String id,
            HttpServletResponse response) {
        PersonEntity entity = service.findById(id);
        if (entity != null) {
            person.id = id;
            return converter.convertEntity(service.update(converter.convertDTO(person)));
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }
    }

    // ------------------------------------------------------------------------
    // DELETE
    // ------------------------------------------------------------------------
    @RequestMapping(path = "/api/person/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") String id, HttpServletResponse response) {
        PersonEntity entity = service.findById(id);
        if (entity != null) {
            service.delete(id);
            response.setStatus(HttpStatus.NO_CONTENT.value());
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }

    // ------------------------------------------------------------------------
    // OPTIONS
    // ------------------------------------------------------------------------
    @RequestMapping(path = "/api/person", method = RequestMethod.OPTIONS)
    ResponseEntity<Void> options() {
        return allows(GET, OPTIONS, DELETE, POST, HEAD, PUT);
    }

}
