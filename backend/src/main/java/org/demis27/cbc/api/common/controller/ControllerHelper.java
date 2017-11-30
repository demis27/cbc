package org.demis27.cbc.api.common.controller;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.demis27.cbc.api.common.range.Range;
import org.demis27.cbc.api.common.range.RangeConverter;
import org.demis27.cbc.api.common.range.RangeException;
import org.demis27.cbc.api.common.range.RequestedRangeUnsatisfiableException;
import org.demis27.cbc.api.common.sort.SortParameterElement;
import org.demis27.cbc.api.common.sort.SortParameterParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerHelper {

    public static ResponseEntity<Void> allows(HttpMethod... methods) {
        HttpHeaders headers = new HttpHeaders();
        Set<HttpMethod> allow = new HashSet();
        for(HttpMethod method: methods){
            allow.add(method);
        }
        headers.setAllow(allow);

        return new ResponseEntity(headers, HttpStatus.NO_CONTENT);
    }

    public static List<SortParameterElement> getSorts(String sortParameters) {
        List<SortParameterElement> sorts;
        if (sortParameters != null && sortParameters.length() > 0) {
            sorts = SortParameterParser.parse(sortParameters);
        } else {
            sorts = Collections.emptyList();
        }
        return sorts;
    }

    public static Range getRange(String requestRange) throws RangeException {
        Range range;

        if (requestRange != null) {
            try {
                range = RangeConverter.parse(requestRange);
            } catch (RequestedRangeUnsatisfiableException e) {
                String reason = "Wrong format for the range parameter. The format is: \"resources: page=[page-number];size=[page-size]\" and the parameter value is: "
                        + requestRange;
                throw new RangeException(reason);
            }
        } else {
            range = new Range(0, 10);
        }
        return range;
    }
}
