package ua.lviv.footgo.controller;

import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.jsonmapper.AdminSignUpRequestJsonbody;

@Controller
@RequestMapping("/admin")
public class AdminAuthController {

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity< String > addTeamViaJson(@RequestBody AdminSignUpRequestJsonbody adminSignUpRequestJsonbody) {

        System.out.println("admin's email + " + adminSignUpRequestJsonbody.getEmail() + " " + "password " + adminSignUpRequestJsonbody.getPassword());

        return new ResponseEntity<>("Admin is created successfully", HttpStatus.CREATED);
    }
}
