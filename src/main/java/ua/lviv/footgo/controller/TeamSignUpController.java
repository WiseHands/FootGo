
package ua.lviv.footgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.TeamSignUpRequest;
import ua.lviv.footgo.jsonmapper.TeamSignUpRequestJsonBody;
import ua.lviv.footgo.repository.TeamSignUpRepository;

@Controller
@RequestMapping(path = "/team")
public class TeamSignUpController {

    @Autowired
    TeamSignUpRepository teamSignUpRepository;




    @RequestMapping(value = "/signuprequest", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity< String > addTeamViaJson(@RequestBody TeamSignUpRequestJsonBody teamSignUpRequestJsonBody) {

        System.out.println("teamSignUpRequestJsonBody " + teamSignUpRequestJsonBody);

        TeamSignUpRequest team = new TeamSignUpRequest();
        team.setTeamName(teamSignUpRequestJsonBody.getTeamName());
        team.setCaptainName(teamSignUpRequestJsonBody.getCaptainName());
        team.setCaptainPhone(teamSignUpRequestJsonBody.getCaptainPhone());
        team.setCaptainEmail(teamSignUpRequestJsonBody.getCaptainEmail());
        team.setPlayerList(teamSignUpRequestJsonBody.getPlayerList());
        teamSignUpRepository.save(team);


        return new ResponseEntity<>("Team is created successfully", HttpStatus.CREATED);
    }

}
