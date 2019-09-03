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

    @GetMapping(value = "/all", consumes = "application/json", produces = "application/json")
    public List<TeamSignUpRequest> getAll() {
        List<TeamSignUpRequest> requests = (List<TeamSignUpRequest>) teamSignUpRepository.findAll();
        return requests;
    }

    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public TeamSignUpRequest getById(@PathVariable Long id) {
        TeamSignUpRequest request = teamSignUpRepository.findById(id).get();
        return request;
    }

    @DeleteMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public void deleteById(@PathVariable Long id) {
        teamSignUpRepository.deleteById(id);
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public TeamSignUpRequest update(@PathVariable Long id, @RequestParam String name, String captainName, String captainPhone, String captainEmail  ) {
        TeamSignUpRequest request = teamSignUpRepository.findById(id).get();
        request.setTeamName(name);
        request.setCaptainName(captainName);
        request.setCaptainPhone(captainPhone);
        request.setCaptainEmail(captainEmail);
        teamSignUpRepository.save(request);
        return request;
    }
}
