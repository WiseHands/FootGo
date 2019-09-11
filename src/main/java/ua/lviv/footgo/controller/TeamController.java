
package ua.lviv.footgo.controller;

import ua.lviv.footgo.entity.*;
import ua.lviv.footgo.jsonmapper.FootballPlayer;
import ua.lviv.footgo.jsonmapper.TeamCreationRequestJsonBody;
import ua.lviv.footgo.repository.CaptainRepository;
import ua.lviv.footgo.repository.TeamRepository;
import ua.lviv.footgo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/team")
public class TeamController {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    CaptainRepository captainRepository;


    @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity< String > addTeamViaJson(@RequestBody TeamCreationRequestJsonBody teamCreationRequestJsonBody) {


        Captain captain = new Captain();
        captain.setCaptainName(teamCreationRequestJsonBody.getCaptain().getName());
        captain.setCaptainPhone(teamCreationRequestJsonBody.getCaptain().getPhone());
        captain.setCaptainEmail(teamCreationRequestJsonBody.getCaptain().getEmail());
        captain = captainRepository.save(captain);


        Team team = new Team();
        team.setTeamName(teamCreationRequestJsonBody.getTeamName());
        team.setCaptain(captain);
        team = teamRepository.save(team);



        for (FootballPlayer footballPlayer : teamCreationRequestJsonBody.getPlayerList()){
            Player player = new Player();
            player.setFirstName(footballPlayer.getFootballPlayerFirstName());
            player.setLastName(footballPlayer.getFootballPlayerLastName());
            player.setTeam(team);
            playerRepository.save(player);
        }

        return new ResponseEntity<>("Team is created successfully", HttpStatus.CREATED);
    }
}
