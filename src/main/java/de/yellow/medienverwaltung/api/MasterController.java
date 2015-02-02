package de.yellow.medienverwaltung.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.yellow.medienverwaltung.business.MasterService;
import de.yellow.medienverwaltung.database.entity.Master;

@Controller
public class MasterController extends AbstractController {

    @RequestMapping(value = "api/master/{id}/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Master> getMaster(@PathVariable("id") long id) {

        MasterService masterService = new MasterService();

        Master master = masterService.getMasterById(id);

        return new ResponseEntity<Master>(master, HttpStatus.OK);
    }

    @RequestMapping(value = "api/masterdto/{id}/", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<MasterDto> getMasterDto(@PathVariable("id") long id) {

        MasterService masterService = new MasterService();

        MasterDto masterDto = masterService.getMasterDtoById(id);

        return new ResponseEntity<MasterDto>(masterDto, HttpStatus.OK);
    }

    @RequestMapping(value = "api/master/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Long> postNewMaster(@RequestBody MasterDto master) {

        LOG.debug(master.toString());

        MasterService service = new MasterService();
        long masterId = service.insertMaster(master);

        return new ResponseEntity<Long>(masterId, HttpStatus.OK);
    }

}
