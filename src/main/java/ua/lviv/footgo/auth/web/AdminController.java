package ua.lviv.footgo.auth.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.lviv.footgo.auth.service.UserDetailsServiceImpl;
import ua.lviv.footgo.auth.service.UserService;
//Admin page for users management controller
@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @GetMapping("/admins")
    public String userList(Model model) {
        model.addAttribute("allUsers", userDetailsServiceImpl.allUsers());
        return "admins";
    }

    @PostMapping("/admins")
    public String deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userDetailsServiceImpl.deleteUser(userId);
        }
        return "redirect:/admins";
    }

    @GetMapping("/admins/gt/{userId}")
    public String gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userDetailsServiceImpl.usergtList(userId));
        return "admins";
    }
}