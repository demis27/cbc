package org.demis27.cbc.api.rest.controller;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.demis27.cbc.api.common.controller.ControllerHelper;
import org.demis27.cbc.api.common.converter.Converter;
import org.demis27.cbc.api.common.converter.PersonConverter;
import org.demis27.cbc.api.common.dto.PersonDTO;
import org.demis27.cbc.data.entity.PersonEntity;
import org.demis27.cbc.api.common.range.RangeException;
import org.demis27.cbc.service.EntityService;
import org.demis27.cbc.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController extends Controller<PersonEntity, PersonDTO> {

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
        return super.list(sortParameters, request, response);
    }

    @RequestMapping(path = "/api/person/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public PersonDTO get(@PathVariable(value = "id") String id, HttpServletResponse response) {
        return super.get(id, response);
    }

    // ------------------------------------------------------------------------
    // POST
    // ------------------------------------------------------------------------
    @RequestMapping(path = "/api/person", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public PersonDTO post(@RequestBody @Valid final PersonDTO dto, HttpServletResponse response) {
        return super.post(dto, response);
    }

    // ------------------------------------------------------------------------
    // PUT
    // ------------------------------------------------------------------------
    @RequestMapping(path = "/api/person/{id}", method = RequestMethod.PUT, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public PersonDTO put(@RequestBody @Valid final PersonDTO dto, @PathVariable(value = "id") String id,
            HttpServletResponse response) {
        return super.put(dto, id, response);
    }

    // ------------------------------------------------------------------------
    // DELETE
    // ------------------------------------------------------------------------
    @RequestMapping(path = "/api/person/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") String id, HttpServletResponse response) {
        super.delete(id, response);
    }

    // ------------------------------------------------------------------------
    // OPTIONS
    // ------------------------------------------------------------------------
    @RequestMapping(path = "/api/person", method = RequestMethod.OPTIONS)
    ResponseEntity<Void> options() {
        return ControllerHelper.allows(GET, OPTIONS, DELETE, POST, HEAD, PUT);
    }

    @Override
    protected EntityService<PersonEntity> getService() {
        return service;
    }

    @Override
    protected Converter<PersonEntity, PersonDTO> getConverter() {
        return converter;
    }
}
