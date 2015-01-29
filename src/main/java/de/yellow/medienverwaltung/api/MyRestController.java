package de.yellow.medienverwaltung.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//TODO: Löschen, wenn nicht mehr als Vorlage benötigt

//Annotation für Controller-Klassen
@Controller
public class MyRestController extends AbstractController {

    // Mapping auf /medienverwaltung/api/dto, Rückgabe im JSON-Format. Es kann
    // ein optionaler Parameter "input" mitgegeben werden (?input=blub)
    @RequestMapping(value = "api/dto", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<DataTransferObject> getDto(@RequestParam(value = "input", required = false) String input) {

        String output = "";

        if (input != null) {
            output = input;
        }

        // ResponseEntity mit Nutzlast und HTTP-Status 200 OK. Das
        // "DataTransferObject" ist ein simples POJO mit ein paar Gettern
        // und Settern + Standardkonstruktor
        return new ResponseEntity<DataTransferObject>(new DataTransferObject(10, output), HttpStatus.OK);

    }

    // weitere Methoden für POST / PUT / DELETE...

}
