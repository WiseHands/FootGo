package ua.lviv.footgo.routing;

import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.Game;
import ua.lviv.footgo.entity.League;
import ua.lviv.footgo.entity.Player;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.jsonmapper.PlayerGoals;
import ua.lviv.footgo.jsonmapper.TeamResults;
import ua.lviv.footgo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ua.lviv.footgo.service.GameFinder;
import ua.lviv.footgo.service.ResultService;
import ua.lviv.footgo.service.TopScorerService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HttpRequestsController {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamSignUpRepository teamSignUpRepository;

    @Autowired
    TourRepository tourRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    ResultService resultService;

    @Autowired
    GameFinder gameFinder;

    @Autowired
    TopScorerService topScorerService;

    @Autowired
    LeagueManagementRepository leagueManagementRepository;

    @GetMapping({"/"})
    public String footgo(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("name", name);
        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        model.addAttribute("league", leagueList.get(0));
        List allTeams = (List) teamRepository.findAll();
        model.addAttribute("teamList", allTeams);
        return "footgo";
    }

    @GetMapping({"/signup"})
    public String signup(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("name", name);
        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        model.addAttribute("league", leagueList.get(0));
        return "signup";
    }  

    @GetMapping({"/results"})
    public String results(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        List tourList = (List) tourRepository.findAll();
        model.addAttribute("tourList", tourList);
        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        model.addAttribute("league", leagueList.get(0));
        return "results";
    }

    @GetMapping({"/team/{teamId}"})
    public String results(Model model, @PathVariable("teamId") Long teamId) {
        Team team = teamRepository.findById(teamId).get();

        model.addAttribute("gameList", gameFinder.findAllGamesForTeam(team));
        model.addAttribute("team", team);
        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        model.addAttribute("league", leagueList.get(0));


        List<TeamResults> results = resultService.getResults(true);
        Integer position = 1;
        for(int i=0; i<results.size(); i++) {
            TeamResults result = results.get(i);

            if(result.getTeam().getId().equals(teamId)) {
                position +=i;
            }
        }
        model.addAttribute("position", position);


        return "teamresults";
    }

    @GetMapping({"/gametable"})
    public String gametable(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        List<TeamResults> results = resultService.getResults(true);


        List allTeams = (List) teamRepository.findAll();
        model.addAttribute("firstPlace", results.get(0));
        model.addAttribute("secondPlace", results.get(1));
        model.addAttribute("thirdPlace", results.get(2));
        model.addAttribute("teamList", results.subList(3, allTeams.size()));
        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        model.addAttribute("league", leagueList.get(0));
        System.out.println("\n\nallTeams:");
        System.out.println(allTeams);
        return "gametable";
    }

    @GetMapping({"/bombardier"})
    public String bombardier(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        List<PlayerGoals> playerGoals = topScorerService.getResults();
        model.addAttribute("playerGoals", playerGoals);
        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        model.addAttribute("league", leagueList.get(0));
        return "bombardier";
    }

    @GetMapping({"/game/{id}"})
    public String gameDetails(Model model, @PathVariable("id") Long id) {
        Game game = gameRepository.findById(id).get();
        model.addAttribute("game", game);
        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        model.addAttribute("league", leagueList.get(0));
        return "game";
    }

    @GetMapping({"/admin"})
    public String submission(Model model) {
        return "Admin";
    }

    @GetMapping({"/admin/tour"})
    public String tourList(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("tourList", tourRepository.findAll());
        return "AdminTourList";
    }

    @GetMapping({"/admin/tour/details"})
    public String tourDetails(Model model, @RequestParam long uuid) {
        model.addAttribute("tour", tourRepository.findById(uuid).get());
        return "AdminTourDetails";
    }

    @GetMapping({"/admin/submission"})
    public String submission(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("submissions", teamSignUpRepository.findAll());
        return "AdminSubmissionProgress";
    }

    @GetMapping(value = "/admin/submission/edit")
    public String submissionEdit(Model model, @RequestParam long uuid) {
        return "AdminSubmissionEntryEdit";
    }

    @GetMapping(value = "/admin/match/edit")
    public String matchesReview(Model model, @RequestParam long uuid) {
        model.addAttribute("game", gameRepository.findById(uuid).get());
        return "AdminMatchDetail";
    }

    @GetMapping({"/admin/team"})
    public String teamList(Model model) {
        model.addAttribute("teamList", teamRepository.findAll());
        return "AdminSubmissionTeamList";
    }

    @GetMapping(value = "/admin/team/edit")
    public String teamEdit(Model model, @RequestParam long uuid) {
        model.addAttribute("team", teamRepository.findById(uuid).get());
        return "AdminSubmissionTeamEntryEdit";
    }

    @GetMapping(value = "/admin/player/edit")
    public String playerEdit(Model model, @RequestParam long uuid) {
        model.addAttribute("player", teamRepository.findById(uuid));
        return "AdminSubmissionPlayerEntryEdit";
    }

    @GetMapping(value = "/admin/team/player/add")
    public String playerAdd(Model model) {
        return "AdminTeamPlayerEntryAdd";
    }

    @GetMapping({"/player/{id}"})
    public String playersDetails(Model model, @PathVariable("id") Long id) {
        Player player = playerRepository.findById(id).get();
        model.addAttribute("player", player);
        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        model.addAttribute("league", leagueList.get(0));
        return "player";
    }

}
