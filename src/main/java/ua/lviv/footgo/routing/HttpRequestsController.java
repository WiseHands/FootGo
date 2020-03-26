package ua.lviv.footgo.routing;

import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.*;
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
import java.util.Optional;

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

    @Autowired
    SeasonRepository seasonRepository;

/*    @GetMapping({"/"})
    public String footgo(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("name", name);
        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        model.addAttribute("league", leagueList.get(0));
        List allTeams = (List) teamRepository.findAll();
        model.addAttribute("teamList", allTeams);
        return "footgo";
    }*/

    @GetMapping({"/"})
    public String footgo(Model model, @RequestParam(value = "name", required = false) String name) {
        model.addAttribute("name", name);
        List<Season> seasonList = (List<Season>) seasonRepository.findAll();
        model.addAttribute("season", seasonList.get(0));
        return "seasons";
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

    @GetMapping({"/admin/season/{id}"})
    public String seasonList(Model model, @PathVariable("id") Long id) {
        Season season = seasonRepository.findById(id).get();
        model.addAttribute("season", season);
        return "AdminSeason";
    }

    @GetMapping({"/admin/season/{id}/leaguelist/{leagueId}/tour"})
    public String tourList(Model model, @PathVariable("leagueId") Long leagueId, @PathVariable("id") Long id, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        Season season = seasonRepository.findById(id).get();
        model.addAttribute("season", season);
        League league = leagueManagementRepository.findById(leagueId).get();
        model.addAttribute("league", league);
        /*model.addAttribute("tourList", tourRepository.findAll());*/
        return "AdminTourList";
    }

    @GetMapping({"/admin/season/{id}/leaguelist/{leagueId}/tour/details"})
    public String tourDetails(Model model, @PathVariable("id") Long id, @PathVariable("leagueId") Long leagueId, @RequestParam long uuid) {
        Season season = seasonRepository.findById(id).get();
        model.addAttribute("season", season);
        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        model.addAttribute("leagueList", leagueList);
        League league = leagueManagementRepository.findById(leagueId).get();
        model.addAttribute("league", league);
        model.addAttribute("tour", tourRepository.findById(uuid).get());
        return "AdminTourDetails";
    }

    @GetMapping({"/admin/season/{id}/submission"})
    public String submission(Model model, @PathVariable("id") Long id, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        Season season = seasonRepository.findById(id).get();
        model.addAttribute("season", season);
        model.addAttribute("submissions", teamSignUpRepository.findAll());
        return "AdminSubmissionProgress";
    }

    @GetMapping(value = "/admin/season/{id}/submission/edit")
    public String submissionEdit(Model model, @PathVariable("id") Long id, @RequestParam long uuid) {
        return "AdminSubmissionEntryEdit";
    }

    @GetMapping(value = "/admin/season/{id}/leaguelist/{leagueId}/game/{gameId}")
    public String matchesReview(Model model, @PathVariable("id") Long id, @PathVariable("leagueId") Long leagueId, @PathVariable("gameId") Long gameId) {
        Season season = seasonRepository.findById(id).get();
        model.addAttribute("season", season);
        League league = leagueManagementRepository.findById(leagueId).get();
        model.addAttribute("league", league);
        model.addAttribute("game", gameRepository.findById(gameId).get());
        return "AdminMatchDetail";
    }

    @GetMapping({"/admin/season/{id}/team"})
    public String teamList(Model model, @PathVariable("id") Long id) {
        Season season = seasonRepository.findById(id).get();
        model.addAttribute("season", season);
        return "AdminSubmissionTeamList";
    }

    @GetMapping(value = "/admin/season/{id}/team/edit")
    public String teamEdit(Model model, @PathVariable("id") Long id, @RequestParam long uuid) {
        Season season = seasonRepository.findById(id).get();
        model.addAttribute("season", season);
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
    @GetMapping(value = "/admin")
    public String admin(Model model) {
        List<Season> seasonList = (List<Season>) seasonRepository.findAll();
        model.addAttribute("seasonList", seasonList);
        return "AdminSeasonList";
    }

    @GetMapping(value = "/admin/seasons/new")
    public String seasonAdd(Model model) {
        return "AdminSeasonsCreate";
    }
    @GetMapping(value = "/admin/season/{id}/leaguelist")
    public String leagueList(Model model, @PathVariable("id") Long id) {
        Season season = seasonRepository.findById(id).get();
        model.addAttribute("season", season);
        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        model.addAttribute("leagueList", leagueList);
        return "AdminLeagueList";
    }

    @GetMapping(value = "/admin/season/{id}/team/new")
    public String teamCreate(Model model, @PathVariable("id") Long id) {
        Season season = seasonRepository.findById(id).get();
        model.addAttribute("season", season);
        return "AdminTeamCreate";
    }
    @GetMapping(value = "/admin/season/{id}/cuplist")
    public String cupList(Model model, @PathVariable("id") Long id) {
        return "AdminCupList";
    }
    @GetMapping(value = "/admin/season/{id}/leaguelist/{leagueId}")
    public String league(Model model, @PathVariable("id") Long id, @PathVariable("leagueId") Long leagueId) {
        Season season = seasonRepository.findById(id).get();
        model.addAttribute("season", season);
        League league = leagueManagementRepository.findById(leagueId).get();
        model.addAttribute("league", league);
        return "AdminLeague";
    }
    @GetMapping({"/admin/season/{id}/leaguelist/{leagueId}/team"})
    public String leagueTeamList(Model model, @PathVariable("id") Long id, @PathVariable("leagueId") Long leagueId) {
        Season season = seasonRepository.findById(id).get();
        model.addAttribute("season", season);
        League league = leagueManagementRepository.findById(leagueId).get();
        model.addAttribute("league", league);
        return "AdminSubmissionLeagueTeamList";
    }
    @GetMapping(value = "/admin/cup")
    public String cup(Model model) {
        return "AdminCup";
    }
    @GetMapping(value = "/admin/season/{id}/league/new")
    public String leagueNew(Model model, @PathVariable("id") Long id) {
        Season season = seasonRepository.findById(id).get();
        model.addAttribute("season", season);
        return "AdminLeagueNew";
    }
    @GetMapping(value = "/admin/cup/new")
    public String cupNew(Model model) {
        return "AdminCupNew";
    }
}
