
package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.footgo.jsonmapper.TeamResults;
import ua.lviv.footgo.service.ResultService;

import java.util.List;

@RestController
@RequestMapping(path = "/games")
public class ResultController {

    @Autowired
    ResultService resultService;


    @RequestMapping(value = "/table", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public List<TeamResults> buildResultsTable() {
        return resultService.getResults(true);
    }


}
