
package com.dev2qa.footgo.controller;

import com.dev2qa.footgo.entity.FootballPlayer;
import com.dev2qa.footgo.entity.TeamCreationRequestJsonBody;
import com.dev2qa.footgo.entity.Player;
import com.dev2qa.footgo.entity.Team;
import com.dev2qa.footgo.repository.TeamAccountRepository;
import com.dev2qa.footgo.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/userAccount")
public class TeamAccountController {

    @Autowired
    TeamAccountRepository teamAccountRepository;

    @Autowired
    UserAccountRepository userAccountRepository;


    @RequestMapping(value = "/jsonteamsignup", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity< String > addTeamViaJson(@RequestBody TeamCreationRequestJsonBody teamCreationRequestJsonBody){

        Team team = new Team();
        team.setCaptainName(teamCreationRequestJsonBody.getCaptainName());
        team.setTeamName(teamCreationRequestJsonBody.getTeamName());
        team.setCaptainPhone(teamCreationRequestJsonBody.getCaptainPhone());
        team.setCaptainEmail(teamCreationRequestJsonBody.getCaptainEmail());
        team = teamAccountRepository.save(team);

        for (FootballPlayer footballPlayer : teamCreationRequestJsonBody.getPlayerList()){
            Player player = new Player();
            player.setPlayerName(footballPlayer.getFootballPlayerName());
            player.setTeam(team);
            userAccountRepository.save(player);
        }



        return new ResponseEntity<>("saved team", HttpStatus.OK);
    }


    @GetMapping(path = "/deleteByCaptainName")
    @ResponseBody
    public String deleteTeamByCaptainName(@RequestParam String captain_name) {

        StringBuffer retBuf = new StringBuffer();

        teamAccountRepository.deleteByCaptainName(captain_name);

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

        List<Team> teamList = (List<Team>) teamAccountRepository.findAll();

        if (teamList != null) {
            for (Team team : teamList) {
                retBuf.append("captain name = ");
                retBuf.append(team.getCaptainName());
                retBuf.append(", captain phone = ");
                retBuf.append(team.getCaptainPhone());
                retBuf.append(", email = ");
                retBuf.append(team.getCaptainEmail());
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
