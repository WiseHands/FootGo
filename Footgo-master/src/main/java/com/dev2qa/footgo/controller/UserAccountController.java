package com.dev2qa.footgo.controller;

import java.util.List;

import com.dev2qa.footgo.entity.Player;
import com.dev2qa.footgo.repository.TeamAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    /*
     * Mapping url exmaple: jsonplayersignup
     * http://localhost:8080/userAccount/signup?key=value&key1=value1& ...
     */
    @PostMapping(path = "/playersignup")
    @ResponseBody
    public String addPlayer(@RequestParam String firstName, @RequestParam String lastName, @RequestParam Integer number, @RequestParam String email, @RequestParam String mobile) {

        Player player = new Player();
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setNumber(number);
        player.setEmail(email);
        player.setPhone(mobile);
//        player.setCaptain(isCaptain);

        userAccountRepository.save(player);

        String ret = "User account has been added, user name = " + lastName + ", phone = " + mobile + ", email = " + email;

        return ret;
    }


    @PostMapping(path = "/jsonplayersignup", consumes = "application/json", produces = "application/json")
    public String addPlayerViaJson(@RequestBody  Player player) {

        player.getFirstName();

        userAccountRepository.save(player);

        String ret = "User account has been added via JSON, user name = " + player.getFirstName();
        System.out.println(ret);
        return ret;
    }

//    work with team

    @PostMapping(path = "/jsonteamsignup", consumes = "application/json", produces = "application/json")
    public String addTeamViaJson(@RequestBody  Team team) {

        team.getCaptainName();
        team.getPlayerList();

        teamAccountRepository.save(team);

        String returns = "User team has been added via JSON, captain name = " + team.getCaptainName() + " and players " + team.getPlayerList();
        System.out.println(returns);
        return returns;
    }


    @PostMapping(path = "/teamsignup")
    @ResponseBody
    public String createTeam(@RequestParam String teamName, @RequestParam String captainName,
                             @RequestParam String captainPhone, @RequestParam String captainEmail) {

        Team team = new Team();
        team.setTeamName(teamName);
        team.setCaptainName(captainName);
        team.setCaptainPhone(captainPhone);
        team.setCaptainEmail(captainEmail);
//        team.setPlayerList(players.toString());

        teamAccountRepository.save(team);

        String ret = "Team has been added, user name = " + captainName + ", phone = " + captainPhone
                + ", email = " + captainEmail;

        return ret;
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
