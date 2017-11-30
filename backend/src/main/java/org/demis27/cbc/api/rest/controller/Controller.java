package org.demis27.cbc.api.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.demis27.cbc.api.common.controller.ControllerHelper;
import org.demis27.cbc.api.common.converter.Converter;
import org.demis27.cbc.api.common.dto.DTO;
import org.demis27.cbc.data.entity.Entity;
import org.demis27.cbc.api.common.range.Range;
import org.demis27.cbc.api.common.range.RangeException;
import org.demis27.cbc.service.EntityService;
import org.demis27.cbc.api.common.sort.SortParameterElement;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public abstract class Controller<E extends Entity, D extends DTO> {

    protected List<D> list(String sortParameters, HttpServletRequest request, HttpServletResponse response)
            throws RangeException, InterruptedException {
        response.setHeader(HttpHeaders.ACCEPT_RANGES, "resources");

        Range range = ControllerHelper.getRange(request.getHeader("Range"));
        List<SortParameterElement> sorts = ControllerHelper.getSorts(sortParameters);

        List<D> result = getConverter().convertEntities(getService().findPart(range, sorts));
        if (result == null || result.size() == 0) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
        } else {
            response.setHeader(HttpHeaders.CONTENT_RANGE,
                    "resources " + range.getStart() + "-" + Math.min(range.getEnd(), result.size()) + "/*");
            response.setStatus(HttpStatus.OK.value());
        }

        return result;
    }

    protected D get(String id, HttpServletResponse response) {
        D result = getConverter().convertEntity(getService().findById(id));
        if (result != null) {
            response.setStatus(HttpStatus.OK.value());
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return result;

    }

    protected D post(D dto, HttpServletResponse response) {
        D result = getConverter().convertEntity(getService().create(getConverter().convertDTO(dto)));
        response.setStatus(HttpStatus.CREATED.value());
        return result;
    }

    protected D put(D dto, String id, HttpServletResponse response) {
        E entity = getService().findById(id);
        if (entity != null) {
            dto.id = id;
            return getConverter().convertEntity(getService().update(getConverter().convertDTO(dto)));
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }
    }

    protected void delete(String id, HttpServletResponse response) {
        E entity = getService().findById(id);
        if (entity != null) {
            getService().delete(id);
            response.setStatus(HttpStatus.NO_CONTENT.value());
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }

    }

    protected abstract EntityService<E> getService();

    protected abstract Converter<E, D> getConverter();
}
