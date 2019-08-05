package com.dev2qa.footgo.controller;

import com.dev2qa.footgo.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.dev2qa.footgo.repository.AdminRepository;

@Controller
@RequestMapping(path = "/userAccount")
public class UserAccountController {

    @Autowired
    AdminRepository adminRepository;



    @PostMapping(path = "/jsonplayersignup", consumes = "application/json", produces = "application/json")
    public ResponseEntity <String> addPlayerViaJson(@RequestBody  Player player) {


        adminRepository.save(player);

        String ret = "User account has been added via JSON, user name = " + player.getPlayerName();
        System.out.println(ret);
        return new ResponseEntity<>("saved player", HttpStatus.OK);

    }



}
