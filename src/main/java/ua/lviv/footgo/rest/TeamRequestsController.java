package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.TeamSignUpRequest;
import ua.lviv.footgo.repository.TeamSignUpRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/submission")
public class TeamRequestsController {

    @Autowired
    TeamSignUpRepository teamSignUpRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public List<TeamSignUpRequest> getAll() {
        List<TeamSignUpRequest> requests = (List<TeamSignUpRequest>) teamSignUpRepository.findAll();
        return requests;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public TeamSignUpRequest getById(@PathVariable Long id) {
        TeamSignUpRequest request = teamSignUpRepository.findById(id).get();
        return request;
    }
}
