package com.dev2qa.footgo.controller;

import java.util.List;

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

    /*
     * Mapping url exmaple:
     * http://localhost:8080/userAccount/signup?userName=Jerry&password=888888&email=
     * jerry@dev2qa.com
     * http://localhost:8080/userAccount/signup?userName=Richard&password=888888&email=
     * richard@google.com
     */
    @PostMapping(path = "/signup")
    @ResponseBody
    public String addPlayer(@RequestParam String teamName, @RequestParam String captainName, @RequestParam String captainPhone, @RequestParam String captainEmail) {

        Team team = new Team();
        team.setTeamName(teamName);
        team.setCaptainName(captainName);
        team.setCaptainPhone(captainPhone);
        team.setCaptainEmail(captainEmail);

        userAccountRepository.save(team);

        String ret = "User account has been added, user name = " + captainName + ", phone = " + captainPhone + ", email = "
                + captainEmail;

        return ret;
    }

    /*
     * Mapping url exmaple: http://localhost:8080/userAccount/findAll
     */
    @GetMapping(path = "/findAll")
    @ResponseBody
    public String findAllUser() {

        StringBuffer retBuf = new StringBuffer();

        List<Team> teamList = (List<Team>) userAccountRepository.findAll();

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

        if (retBuf.length() == 0) {
            retBuf.append("No record find.");
        } else {
            retBuf.insert(0, "<pre>");
            retBuf.append("</pre>");
        }

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
