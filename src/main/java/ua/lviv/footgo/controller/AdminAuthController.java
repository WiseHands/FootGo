package ua.lviv.footgo.controller;

import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminAuthController {
    @GetMapping(value="/login", headers="Accept=*/*")
    public ResponseEntity< String > login(@RequestParam String email, @RequestParam String password) {
        System.out.println(email + password);
        return new ResponseEntity<>("Successful login", HttpStatus.OK);
    }
}
