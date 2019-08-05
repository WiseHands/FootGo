
package com.dev2qa.footgo.controller;

import com.dev2qa.footgo.entity.*;
import com.dev2qa.footgo.repository.CaptainRepository;
import com.dev2qa.footgo.repository.TeamRepository;
import com.dev2qa.footgo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            player.setPlayerName(footballPlayer.getFootballPlayerName());
            player.setTeam(team);
            playerRepository.save(player);
        }

        return new ResponseEntity<>("Team is created successfully", HttpStatus.CREATED);
    }
    /*
     * Mapping url exmaple:
     * http://localhost:8080/userAccount/findByTeamName?teamName=team17
     */
    @GetMapping(path = "/findByTeamName")
    @ResponseBody
    public List<Team> findByTeamName(@RequestParam String team_name) {

        StringBuffer retBuf = new StringBuffer();

        List<Team> teamList;
        teamList = teamRepository.findByTeamName(team_name);

        if (teamList != null) {
            for (Team team : teamList) {
                retBuf.append("player list: ");
                retBuf.append(team.getPlayers());
                retBuf.append("\r\n");
            }
        }

        return teamList;
    }
    /*
     * Mapping url exmaple:
     * http://localhost:8080/userAccount/deleteByCaptainName?captainName=Richard
     */
    @GetMapping(path = "/deleteByCaptainName")
    @ResponseBody
    public String deleteTeamByCaptainName(@RequestParam String captain_name) {

        StringBuffer retBuf = new StringBuffer();

        captainRepository.deleteByCaptainName(captain_name);

        retBuf.append("User data has been deleted successfully.");

        return retBuf.toString();
    }

    /*
     * Mapping url exmaple: http://localhost:8080/userAccount/findAll
     */
    @GetMapping(path = "/findAll")
    @ResponseBody
    public String findAllUser() {

        StringBuffer retBuf = new StringBuffer();

        List<Captain> captainList;
        captainList = (List<Captain>) captainRepository.findAll();

        if (captainList != null) {
            for (Captain captain : captainList) {
                retBuf.append("captain name = ");
                retBuf.append(captain.getCaptainName());
                retBuf.append(", captain phone = ");
                retBuf.append(captain.getCaptainPhone());
                retBuf.append(", email = ");
                retBuf.append(captain.getCaptainEmail());
                retBuf.append("\r\n");
            }
        }

//        if (retBuf.length() == 0) {
//            retBuf.append("No record find.");
//        } else {
//            retBuf.insert(0, "<pre>");
//            retBuf.append("</pre>");
//        }

        return retBuf.toString();
    }
}
