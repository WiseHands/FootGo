package com.dev2qa.footgo.controller;

import java.util.List;

import com.dev2qa.footgo.entity.Player;
import com.dev2qa.footgo.repository.TeamAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.dev2qa.footgo.entity.Team;
import com.dev2qa.footgo.repository.UserAccountRepository;

@Controller
@RequestMapping(path = "/userAccount")
public class UserAccountController {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    TeamAccountRepository teamAccountRepository;

    @PostMapping(path = "/jsonplayersignup", consumes = "application/json", produces = "application/json")
    public ResponseEntity <String> addPlayerViaJson(@RequestBody  Player player) {

        player.getFirstName();

        userAccountRepository.save(player);

        String ret = "User account has been added via JSON, user name = " + player.getFirstName();
        System.out.println(ret);
        return new ResponseEntity<>("saved player", HttpStatus.OK);

    }

    @RequestMapping(value = "/jsonteamsignup", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity < String > addTeamViaJson(@RequestBody Team team) {

        team.getCaptainName();
        team.getPlayerList();

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

    /*
     * Mapping url exmaple:
     * http://localhost:8080/userAccount/findByName?userName=Jerry
     */
/*
    @GetMapping(path = "/findByName")
    @ResponseBody
    public String findByName(@RequestParam String userName) {

        StringBuffer retBuf = new StringBuffer();

        List<Team> teamList = (List<Team>) userAccountRepository.findByUsername(userName);

        if (teamList != null) {
            for (Team team : teamList) {
                retBuf.append("user name = ");
                retBuf.append(team.getCaptainName());
                retBuf.append(", captain phone = ");
                retBuf.append(team.getCaptainPhone());
                retBuf.append(", email = ");
                retBuf.append(team.getCaptainEmail());
                retBuf.append("\r\n");
            }
        }

        if (retBuf.length() == 0) {
            retBuf.append("No record find.");
        }

        return retBuf.toString();
    }
*/

    /*
     * Mapping url exmaple:
     * http://localhost:8080/userAccount/findByNameAndPassword?userName=Jerry&
     * password=888888
     */
/*
    @GetMapping(path = "/findByNameAndPassword")
    @ResponseBody
    public String findByNameAndPassword(@RequestParam String userName, @RequestParam String password) {

        StringBuffer retBuf = new StringBuffer();

        List<Team> teamList = (List<Team>) userAccountRepository
                .findByUsernameAndPassword(userName, password);

        if (teamList != null) {
            for (Team team : teamList) {
                retBuf.append("user name = ");
                retBuf.append(team.getCaptainPhone());
                retBuf.append(", captain phone = ");
                retBuf.append(team.getCaptainPhone());
                retBuf.append(", email = ");
                retBuf.append(team.getCaptainEmail());
                retBuf.append("<br/>");
            }
        }

        if (retBuf.length() == 0) {
            retBuf.append("No record find.");
        }

        return retBuf.toString();
    }
*/

    /*
     * Mapping url exmaple:
     * http://localhost:8080/userAccount/updateUser?userName=Jerry&password=hello&
     * email=hello_jerry@gmail.com
     */
/*
    @GetMapping(path = "/updateUser")
    @ResponseBody
    public String updateUser(@RequestParam String userName, @RequestParam String password, @RequestParam String email) {

        StringBuffer retBuf = new StringBuffer();

        List<Team> teamList = userAccountRepository.findByUsername(userName);

        if (teamList != null) {
            for (Team team : teamList) {
                team.setUserName(userName);
                team.setPassword(password);
                team.setEmail(email);
                userAccountRepository.save(team);
            }
        }

        retBuf.append("User data update successfully.");

        return retBuf.toString();
    }
*/

    /*
     * Mapping url exmaple:
     * http://localhost:8080/userAccount/deleteByUserName?userName=Richard
     */
/*
    @GetMapping(path = "/deleteByUserName")
    @ResponseBody
    public String deleteByUserName(@RequestParam String userName) {

        StringBuffer retBuf = new StringBuffer();

        userAccountRepository.deleteByUsername(userName);

        retBuf.append("User data has been deleted successfully.");

        return retBuf.toString();
    }
*/

    /*
     * Mapping url exmaple:
     * http://localhost:8080/userAccount/deleteByUserNameAndPassword?userName=
     * Richard&password=888888
     */
/*
    @GetMapping(path = "/deleteByUserNameAndPassword")
    @ResponseBody
    public String deleteByUserNameAndPassword(@RequestParam String userName, @RequestParam String password) {

        StringBuffer retBuf = new StringBuffer();

        userAccountRepository.deleteByUsernameAndPassword(userName, password);

        retBuf.append("User data has been deleted successfully.");

        return retBuf.toString();
    }
*/

}
