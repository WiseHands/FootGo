package ua.lviv.footgo.routing;

import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.repository.GameRepository;
import ua.lviv.footgo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ua.lviv.footgo.repository.TeamSignUpRepository;
import ua.lviv.footgo.repository.TourRepository;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HttpRequestsController {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamSignUpRepository teamSignUpRepository;

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

    @GetMapping({"/admin/submission"})
    public String submission(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("submissions", teamSignUpRepository.findAll());
        return "/admin/SubmissionProgress";
    }

    @RequestMapping(value = "/admin/submission/edit", method = GET)
    public String submissionEdit(Model model, @RequestParam long uuid) {
        return "/admin/SubmissionEntryEdit";
    }

    @RequestMapping(value = "/admin/matches", method = GET)
    public String matchesReview(Model model) {
        return "/admin/MatchesReview";
    }

    @GetMapping({"/login"})
    public String login(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("name", name);

        return "/admin/loginAdminForm";
    }

    @RequestMapping(value = "/.well-known/acme-challenge/{uuid}", method = GET)
    @ResponseBody
    public String submissionEdit(Model model, @PathVariable String uuid) {
        return uuid;
    }

}
