package ua.lviv.footgo.routing;

import ua.lviv.footgo.repository.GameRepository;
import ua.lviv.footgo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.lviv.footgo.repository.TourRepository;

import java.util.List;

@Controller
public class HttpRequestsController {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TourRepository tourRepository;

    @Autowired
    GameRepository gameRepository;

    @GetMapping({"/", "/footgo"})
    public String footgo(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("name", name);
        return "footgo";
    }

    @GetMapping({"/signup"})
    public String signup(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("name", name);
        return "signup";
    }

    @GetMapping({"/results"})
    public String results(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        List tourList = (List) tourRepository.findAll();
        model.addAttribute("tourList", tourList);
        return "results";
    }

    @GetMapping({"/gametable"})
    public String gametable(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        List allTeams = (List) teamRepository.findAll();
        model.addAttribute("firstPlace", allTeams.get(0));
        model.addAttribute("secondPlace", allTeams.get(1));
        model.addAttribute("thirdPlace", allTeams.get(2));
        model.addAttribute("teamList", allTeams.subList(3, allTeams.size()));
        System.out.println("\n\nallTeams:");
        System.out.println(allTeams);
        return "gametable";
    }

    @GetMapping({"/bombardier"})
    public String bombardier(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("name", name);
        return "bombardier";
    }

    @GetMapping({"/login"})
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "loginAdminForm";
    }

}
