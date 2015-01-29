package de.yellow.medienverwaltung.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public abstract class AbstractController {

    protected static final Logger LOG = LoggerFactory.getLogger(AbstractController.class);

    @ExceptionHandler({ Exception.class })
    public @ResponseBody
    ResponseEntity<String> handleError(Exception e) {

        LOG.error(e.getMessage());

        return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
