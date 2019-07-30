package com.dev2qa.footgo.controller;

import com.dev2qa.footgo.entity.Gamer;
import com.dev2qa.footgo.entity.JsonBody;
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
    public ResponseEntity< String > addTeamViaJson(@RequestBody JsonBody jsonBody){

        Player player = new Player();
        for (Gamer gamer : jsonBody.getPlayerList()){
            player.setPlayerName(gamer.getGamerName());
            userAccountRepository.save(player);
        }


        System.out.println("pla " + jsonBody.getPlayerList().size());

        Team team = new Team();
        team.setCaptainName(jsonBody.getCaptainName());
        team.setTeamName(jsonBody.getTeamName());
        team.setCaptainPhone(jsonBody.getCaptainPhone());
        team.setCaptainEmail(jsonBody.getCaptainEmail());

        teamAccountRepository.save(team);

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
