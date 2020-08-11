package ua.lviv.footgo.routing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ua.lviv.footgo.entity.*;
import ua.lviv.footgo.jsonmapper.PlayerGoals;
import ua.lviv.footgo.jsonmapper.TeamResults;
import ua.lviv.footgo.repository.*;
import ua.lviv.footgo.service.GameFinder;
import ua.lviv.footgo.service.ResultService;
import ua.lviv.footgo.service.TopScorerService;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

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
    CupManagementRepository cupManagementRepository;

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    TournamentRepository tournamentRepository;

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
        List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAll();
        if (!tournaments.isEmpty()) {
            Tournament tournament = tournaments.get(0);
            model.addAttribute("tournament", tournament);
            Season season = tournament.getActiveSeason();
            if (!isNull(season)) {
                model.addAttribute("season", season);
                List<League> leagueList = season.getLeagueList();
                model.addAttribute("leagueList", leagueList);
                List<Cup> cupList = season.getCupList();
                model.addAttribute("cupList", cupList);
            }
            List<Season> seasonList = tournament.getSeasonList().stream()
                    .filter(s -> !s.getId().equals(season.getId()))
                    .collect(Collectors.toList());
/*            List<Season> seasonList = seasonRepository.getSeasonsExceptActive(season.getId());*/
            model.addAttribute("seasonList", seasonList);
        }
        return "footgo";
    }
    @GetMapping({"/signup"})
    public String signup(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("name", name);
        List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAll();
        if (!tournaments.isEmpty()) {
            Tournament tournament = tournaments.get(0);
            model.addAttribute("tournament", tournament);
            Season season = tournament.getActiveSeason();
            if (!isNull(season)) {
                model.addAttribute("season", season);
                List<League> leagueList = season.getLeagueList();
                model.addAttribute("leagueList", leagueList);
                List<Cup> cupList = season.getCupList();
                model.addAttribute("cupList", cupList);
            }
            List<Season> seasonList = tournament.getSeasonList().stream()
                    .filter(s -> !s.getId().equals(season.getId()))
                    .collect(Collectors.toList());
            model.addAttribute("seasonList", seasonList);
        }
        /*        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        model.addAttribute("league", leagueList.get(0));*/
        return "signup";
    }
    @GetMapping({"/league/{leagueId}/results"})
    public String results(Model model, @PathVariable("leagueId") Long leagueId, @RequestParam(value = "name", required = false) String name) {
        List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAll();
        Tournament tournament = tournaments.get(0);
        model.addAttribute("tournament", tournament);
        Season season = tournament.getActiveSeason();
        model.addAttribute("season", season);
        List<League> leagueList = season.getLeagueList();
        model.addAttribute("leagueList", leagueList);
        List<Cup> cupList = season.getCupList();
        model.addAttribute("cupList", cupList);
        List<Season> seasonList = tournament.getSeasonList().stream()
                .filter(s -> !s.getId().equals(season.getId()))
                .collect(Collectors.toList());
        model.addAttribute("seasonList", seasonList);
        League league = leagueManagementRepository.findById(leagueId).get();
        model.addAttribute("league", league);
        List<Tour> tourList = league.getTours();
        model.addAttribute("tourList", tourList);

/*        List tourList = (List) tourRepository.findAll();
        model.addAttribute("tourList", tourList);
        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        model.addAttribute("league", leagueList.get(0));*/
        return "results";
    }
    @GetMapping({"/cup/{cupId}/results"})
    public String cupResults(Model model, @PathVariable("cupId") Long cupId, @RequestParam(value = "name", required = false) String name) {
        List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAll();
        Tournament tournament = tournaments.get(0);
        model.addAttribute("tournament", tournament);
        Season season = tournament.getActiveSeason();
        model.addAttribute("season", season);
        List<League> leagueList = season.getLeagueList();
        model.addAttribute("leagueList", leagueList);
        List<Cup> cupList = season.getCupList();
        model.addAttribute("cupList", cupList);
        List<Season> seasonList = tournament.getSeasonList().stream()
                .filter(s -> !s.getId().equals(season.getId()))
                .collect(Collectors.toList());
        model.addAttribute("seasonList", seasonList);
        Cup cup = cupManagementRepository.findById(cupId).get();
        model.addAttribute("cup", cup);
        model.addAttribute("cupId", cupId);
        List<Tour> tourList = cup.getTours();
        model.addAttribute("tourList", tourList);

        return "cupResults";
    }
    @GetMapping({"/league/{leagueId}"})
    public String leagueDetails(Model model, @PathVariable("leagueId") Long leagueId) {
        List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAll();
        Tournament tournament = tournaments.get(0);
        model.addAttribute("tournament", tournament);
        Season season = tournament.getActiveSeason();
        model.addAttribute("season", season);
        List<League> leagueList = season.getLeagueList();
        model.addAttribute("leagueList", leagueList);
        List<Cup> cupList = season.getCupList();
        model.addAttribute("cupList", cupList);
        List<Season> seasonList = tournament.getSeasonList().stream()
                .filter(s -> !s.getId().equals(season.getId()))
                .collect(Collectors.toList());
        model.addAttribute("seasonList", seasonList);
        League league = leagueManagementRepository.findById(leagueId).get();
        model.addAttribute("league", league);
        /*Tour tour = (Tour) league.getTours();*/
        List<Tour> tourList = league.getTours();
        model.addAttribute("tourList", tourList);

        List<Game> gameList = new ArrayList<Game>();
        List<Game> finalGameList = gameList;
        tourList.forEach(tour -> {
            List<Game> _gameList = tour.getGameList();
            finalGameList.addAll(_gameList);
        });

        OffsetDateTime sevenDaysBeforeNow = OffsetDateTime.now().minusDays(7);
        OffsetDateTime sevenDaysAfterNow = OffsetDateTime.now().plusDays(7);

        gameList = gameList.stream().filter(g ->
                g.getGameTime().isAfter(sevenDaysBeforeNow) && g.getGameTime().isBefore(sevenDaysAfterNow)
        ).collect(Collectors.toList());

        gameList.sort(Comparator.comparing(Game::getGameTime));
        gameList.forEach(game -> {
            System.out.println(game.getGameTime());
        });
        model.addAttribute("gameList", gameList);

        List<TeamResults> results = resultService.getResultsByLeague(true, leagueId);
/*        model.addAttribute("firstPlace", results.get(0));
        model.addAttribute("secondPlace", results.get(1));
        model.addAttribute("thirdPlace", results.get(2));*/
        model.addAttribute("resultList", results);
        //List<PlayerGoals> playerGoals = topScorerService.getResults();
        List<PlayerGoals> playerGoals = topScorerService.getResultsByLeague(leagueId).stream().limit(10).collect(Collectors.toList());
        model.addAttribute("playerGoals", playerGoals);
        List<Game> games = (List<Game>) gameRepository.findAll();
        Game game = games.get(0);
        model.addAttribute("game", game);

        return "leagueDetails";
    }
    @GetMapping({"/league/{leagueId}/team/{teamId}"})
    public String leagueResults(Model model,@PathVariable("leagueId") Long leagueId, @PathVariable("teamId") Long teamId) {
        List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAll();
        Tournament tournament = tournaments.get(0);
        model.addAttribute("tournament", tournament);
        Season season = tournament.getActiveSeason();
        model.addAttribute("season", season);
        List<League> leagueList = season.getLeagueList();
        model.addAttribute("leagueList", leagueList);
        List<Cup> cupList = season.getCupList();
        model.addAttribute("cupList", cupList);
        List<Season> seasonList = tournament.getSeasonList().stream()
                .filter(s -> !s.getId().equals(season.getId()))
                .collect(Collectors.toList());
        model.addAttribute("seasonList", seasonList);
/*        List<League> leagueList = season.getLeagueList();
        model.addAttribute("leagueList", leagueList);*/

        League league = leagueManagementRepository.findById(leagueId).get();
        model.addAttribute("league", league);

        Team team = teamRepository.findById(teamId).get();

        /*List<Team> team = league.getTeamList();*/

/*        Team team = teamRepository.findById(teamId).get();*/
        List<Game> gameList = gameFinder.findAllGamesForTeam(team);
        gameList.sort(Comparator.comparing(Game::getGameTime));

        model.addAttribute("gameList", gameList);
        model.addAttribute("team", team);
        /*List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        model.addAttribute("league", leagueList.get(0));*/

        List<TeamResults> results = resultService.getResultsByLeague(true, leagueId);
        Integer position = 1;
        for(int i=0; i<results.size(); i++) {
            TeamResults result = results.get(i);

            if(result.getTeam().getId().equals(teamId)) {
                position +=i;
            }
        }
        model.addAttribute("position", position);

        return "leagueTeamResults";
    }
    @GetMapping({"/cup/{cupId}"})
    public String cupDetails(Model model,@PathVariable("cupId") Long cupId) {
        List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAll();
        Tournament tournament = tournaments.get(0);
        model.addAttribute("tournament", tournament);
        Season season = tournament.getActiveSeason();
        model.addAttribute("season", season);
        List<League> leagueList = season.getLeagueList();
        model.addAttribute("leagueList", leagueList);
        List<Cup> cupList = season.getCupList();
        model.addAttribute("cupList", cupList);
        List<Season> seasonList = tournament.getSeasonList().stream()
                .filter(s -> !s.getId().equals(season.getId()))
                .collect(Collectors.toList());
        model.addAttribute("seasonList", seasonList);
        Cup cup = cupManagementRepository.findById(cupId).get();
        model.addAttribute("cup", cup);
        List<Tour> tourList = cup.getTours();
        model.addAttribute("tourList", tourList);

        List<PlayerGoals> playerGoals = topScorerService.getResultsByCup(cupId).stream().limit(10).collect(Collectors.toList());;
        model.addAttribute("playerGoals", playerGoals);

        List<Game> gameList = new ArrayList<Game>();
        List<Game> finalGameList = gameList;
        tourList.forEach(tour -> {
            List<Game> _gameList = tour.getGameList();
            finalGameList.addAll(_gameList);
        });

        OffsetDateTime sevenDaysBeforeNow = OffsetDateTime.now().minusDays(7);
        OffsetDateTime sevenDaysAfterNow = OffsetDateTime.now().plusDays(7);

        gameList = gameList.stream().filter(g ->
                g.getGameTime().isAfter(sevenDaysBeforeNow) && g.getGameTime().isBefore(sevenDaysAfterNow)
        ).collect(Collectors.toList());

        gameList.sort(Comparator.comparing(Game::getGameTime));
        gameList.forEach(game -> {
            System.out.println(game.getGameTime());
        });
        model.addAttribute("gameList", gameList);

        List<TeamResults> results = resultService.getResultsByCup(true, cupId);
        model.addAttribute("resultList", results);
        List<Game> games = (List<Game>) gameRepository.findAll();
        Game game = games.get(0);
        model.addAttribute("game", game);

        return "cupDetails";
    }
    @GetMapping({"/cup/{cupId}/team/{teamId}"})
    public String cupResults(Model model, @PathVariable("cupId") Long cupId, @PathVariable("teamId") Long teamId) {
        List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAll();
        Tournament tournament = tournaments.get(0);
        model.addAttribute("tournament", tournament);
        Season season = tournament.getActiveSeason();
        model.addAttribute("season", season);

        List<League> leagueList = season.getLeagueList();
        model.addAttribute("leagueList", leagueList);
        List<Cup> cupList = season.getCupList();
        model.addAttribute("cupList", cupList);
        List<Season> seasonList = tournament.getSeasonList().stream()
                .filter(s -> !s.getId().equals(season.getId()))
                .collect(Collectors.toList());
        model.addAttribute("seasonList", seasonList);

        Cup cup = cupManagementRepository.findById(cupId).get();
        model.addAttribute("cup", cup);

        Team team = teamRepository.findById(teamId).get();

        List<Game> gameList = gameFinder.findAllGamesForTeam(team);
        gameList.sort(Comparator.comparing(Game::getGameTime));

        model.addAttribute("gameList", gameList);
        model.addAttribute("team", team);
/*        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        model.addAttribute("league", leagueList.get(0));*/

        List<TeamResults> results = resultService.getResultsByCup(true, cupId);
        Integer position = 1;
        for(int i=0; i<results.size(); i++) {
            TeamResults result = results.get(i);

            if(result.getTeam().getId().equals(teamId)) {
                position +=i;
            }
        }
        model.addAttribute("position", position);

        return "cupTeamResults";
    }
    @GetMapping({"/league/{leagueId}/gametable"})
    public String gametable(Model model, @PathVariable("leagueId") Long leagueId, @RequestParam(value = "name", required = false) String name) {
        List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAll();
        Tournament tournament = tournaments.get(0);
        model.addAttribute("tournament", tournament);
        Season season = tournament.getActiveSeason();
        model.addAttribute("season", season);
        List<League> leagueList = season.getLeagueList();
        model.addAttribute("leagueList", leagueList);
        List<Cup> cupList = season.getCupList();
        model.addAttribute("cupList", cupList);
        List<Season> seasonList = tournament.getSeasonList().stream()
                .filter(s -> !s.getId().equals(season.getId()))
                .collect(Collectors.toList());
        model.addAttribute("seasonList", seasonList);
        League league = leagueManagementRepository.findById(leagueId).get();
        model.addAttribute("league", league);
        List<Tour> tourList = league.getTours();
        model.addAttribute("tourList", tourList);
        List<TeamResults> results = resultService.getResultsByLeague(true, leagueId);
        model.addAttribute("resultList", results);

/*        List<TeamResults> results = resultService.getResults(true);
        List allTeams = (List) teamRepository.findAll();
        model.addAttribute("firstPlace", results.get(0));
        model.addAttribute("secondPlace", results.get(1));
        model.addAttribute("thirdPlace", results.get(2));
        model.addAttribute("teamList", results.subList(3, allTeams.size()));
        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        model.addAttribute("league", leagueList.get(0));
        System.out.println("\n\nallTeams:");
        System.out.println(allTeams);*/
        return "gametable";
    }
    @GetMapping({"/league/{leagueId}/bombardier"})
    public String bombardier(Model model, @PathVariable("leagueId") Long leagueId, @RequestParam(value = "name", required = false) String name) {
        List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAll();
        Tournament tournament = tournaments.get(0);
        model.addAttribute("tournament", tournament);
        Season season = tournament.getActiveSeason();
        model.addAttribute("season", season);
        List<League> leagueList = season.getLeagueList();
        model.addAttribute("leagueList", leagueList);
        List<Cup> cupList = season.getCupList();
        model.addAttribute("cupList", cupList);
        List<Season> seasonList = tournament.getSeasonList().stream()
                .filter(s -> !s.getId().equals(season.getId()))
                .collect(Collectors.toList());
        model.addAttribute("seasonList", seasonList);
        League league = leagueManagementRepository.findById(leagueId).get();
        model.addAttribute("league", league);

        List<PlayerGoals> playerGoals = topScorerService.getResultsByLeague(leagueId);
        model.addAttribute("playerGoals", playerGoals);

/*        List<PlayerGoals> playerGoals = topScorerService.getResults();
        model.addAttribute("playerGoals", playerGoals);
        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        model.addAttribute("league", leagueList.get(0));*/
        return "bombardier";
    }
    @GetMapping({"/cup/{cupId}/bombardier"})
    public String cupBombardier(Model model, @PathVariable("cupId") Long cupId, @RequestParam(value = "name", required = false) String name) {
        List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAll();
        Tournament tournament = tournaments.get(0);
        model.addAttribute("tournament", tournament);
        Season season = tournament.getActiveSeason();
        model.addAttribute("season", season);
        List<League> leagueList = season.getLeagueList();
        model.addAttribute("leagueList", leagueList);
        List<Cup> cupList = season.getCupList();
        model.addAttribute("cupList", cupList);
        List<Season> seasonList = tournament.getSeasonList().stream()
                .filter(s -> !s.getId().equals(season.getId()))
                .collect(Collectors.toList());
        model.addAttribute("seasonList", seasonList);
        Cup cup = cupManagementRepository.findById(cupId).get();
        model.addAttribute("cup", cup);

        List<PlayerGoals> playerGoals = topScorerService.getResultsByCup(cupId);
        model.addAttribute("playerGoals", playerGoals);

        return "cupBombardier";
    }
    @GetMapping({"/league/{leagueId}/game/{gameId}"})
    public String gameLeagueDetails(Model model, @PathVariable("leagueId") Long leagueId, @PathVariable("gameId") Long gameId) {
        List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAll();
        Tournament tournament = tournaments.get(0);
        Season season = tournament.getActiveSeason();
        model.addAttribute("season", season);
        List<League> leagueList = season.getLeagueList();
        model.addAttribute("leagueList", leagueList);
        List<Cup> cupList = season.getCupList();
        model.addAttribute("cupList", cupList);
        List<Season> seasonList = tournament.getSeasonList().stream()
                .filter(s -> !s.getId().equals(season.getId()))
                .collect(Collectors.toList());
        model.addAttribute("seasonList", seasonList);
        Game game = gameRepository.findById(gameId).get();
        model.addAttribute("game", game);
        League league = leagueManagementRepository.findById(leagueId).get();
        model.addAttribute("league", league);
        //List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        //model.addAttribute("league", leagueList.get(0));
        return "gameLeague";
    }
    @GetMapping({"/cup/{cupId}/game/{gameId}"})
    public String gameCupDetails(Model model, @PathVariable("cupId") Long cupId, @PathVariable("gameId") Long gameId) {
        List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAll();
        Tournament tournament = tournaments.get(0);
        Season season = tournament.getActiveSeason();
        model.addAttribute("season", season);
        List<League> leagueList = season.getLeagueList();
        model.addAttribute("leagueList", leagueList);
        List<Cup> cupList = season.getCupList();
        model.addAttribute("cupList", cupList);
        List<Season> seasonList = tournament.getSeasonList().stream()
                .filter(s -> !s.getId().equals(season.getId()))
                .collect(Collectors.toList());
        model.addAttribute("seasonList", seasonList);
        Game game = gameRepository.findById(gameId).get();
        model.addAttribute("game", game);
        Cup cup = cupManagementRepository.findById(cupId).get();
        model.addAttribute("cup", cup);
        return "gameCup";
    }
    @GetMapping({"/admin/tournament/{tournamentId}/season/{seasonId}"})
    public String seasonList(Model model, @PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        return "AdminSeason";
    }
    @GetMapping({"/admin/tournament/{tournamentId}/season/{seasonId}/leaguelist/{leagueId}/tour/details"})
    public String tourDetails(Model model, @PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId, @PathVariable("leagueId") Long leagueId, @RequestParam long uuid) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        List<League> leagueList = season.getLeagueList();
        model.addAttribute("leagueList", leagueList);
/*        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();dfhdfghf
        model.addAttribute("leagueList", leagueList);*/
        League league = leagueManagementRepository.findById(leagueId).get();
        model.addAttribute("league", league);
        model.addAttribute("tour", tourRepository.findById(uuid).get());
        return "AdminTourDetails";
    }
    @GetMapping({"/admin/tournament/{tournamentId}/season/{seasonId}/submission"})
    public String submission(Model model, @PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId, @RequestParam(value = "name", required = false) String name) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        model.addAttribute("submissions", teamSignUpRepository.findAll());
        return "AdminSubmissionProgress";
    }
    @GetMapping(value = "/admin/tournament/{tournamentId}/season/{seasonId}/submission/edit")
    public String submissionEdit(Model model, @PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId, @RequestParam long uuid) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        return "AdminSubmissionEntryEdit";
    }
    @GetMapping(value = "/admin/tournament/{tournamentId}/season/{seasonId}/leaguelist/{leagueId}/game/{gameId}")
    public String matchesReview(Model model,@PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId, @PathVariable("leagueId") Long leagueId, @PathVariable("gameId") Long gameId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        League league = leagueManagementRepository.findById(leagueId).get();
        model.addAttribute("league", league);
        model.addAttribute("game", gameRepository.findById(gameId).get());
        return "AdminMatchDetail";
    }
    @GetMapping(value = "/admin/tournament/{tournamentId}/season/{seasonId}/cuplist/{cupId}/game/{gameId}")
    public String cupMatchesReview(Model model, @PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId, @PathVariable("cupId") Long cupId, @PathVariable("gameId") Long gameId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        Cup cup = cupManagementRepository.findById(cupId).get();
        model.addAttribute("cup", cup);
        model.addAttribute("game", gameRepository.findById(gameId).get());
        return "AdminCupMatchDetail";
    }
    @GetMapping({"/admin/tournament/{tournamentId}/season/{seasonId}/team"})
    public String teamList(Model model, @PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        return "AdminSubmissionTeamList";
    }
    @GetMapping(value = "/admin/tournament/{tournamentId}/season/{seasonId}/team/edit")
    public String teamEdit(Model model, @PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId, @RequestParam long uuid) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        model.addAttribute("team", teamRepository.findById(uuid).get());
        return "AdminSubmissionTeamEntryEdit";
    }
    @GetMapping(value = "/admin/tournament/{tournamentId}/season/{seasonId}/team/player/edit")
    public String playerEdit(Model model,@PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId, @RequestParam long uuid) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        model.addAttribute("player", teamRepository.findById(uuid));
        return "AdminSubmissionPlayerEntryEdit";
    }
    @GetMapping(value = "/admin/tournament/{tournamentId}/season/{seasonId}/team/player/add")
    public String playerAdd(Model model, @PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        return "AdminTeamPlayerEntryAdd";
    }
    @GetMapping({"/league/{leagueId}/player/{playerId}"})
    public String playersLeagueDetails(Model model, @PathVariable("leagueId") Long leagueId, @PathVariable("playerId") Long playerId) {
        List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAll();
        Tournament tournament = tournaments.get(0);
        Season season = tournament.getActiveSeason();
        model.addAttribute("season", season);
        List<League> leagueList = season.getLeagueList();
        model.addAttribute("leagueList", leagueList);
        List<Cup> cupList = season.getCupList();
        model.addAttribute("cupList", cupList);
        List<Season> seasonList = tournament.getSeasonList().stream()
                .filter(s -> !s.getId().equals(season.getId()))
                .collect(Collectors.toList());
        model.addAttribute("seasonList", seasonList);
        Player player = playerRepository.findById(playerId).get();
        model.addAttribute("player", player);
        model.addAttribute("playerFirstNameTransliterated", player.getPlayerFirstNameTransliterated());
        model.addAttribute("playerLastNameTransliterated", player.getPlayerLastNameTransliterated());
        model.addAttribute("teamNameTransliterated", player.getTeam().getTeamNameTransliterated());
/*        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        model.addAttribute("league", leagueList.get(0));*/
        League league = leagueManagementRepository.findById(leagueId).get();
        model.addAttribute("league", league);
        return "playerLeague";
    }
    @GetMapping({"/cup/{cupId}/player/{playerId}"})
    public String playersCupDetails(Model model, @PathVariable("cupId") Long cupId, @PathVariable("playerId") Long playerId) {
        List<Tournament> tournaments = (List<Tournament>) tournamentRepository.findAll();
        Tournament tournament = tournaments.get(0);
        Season season = tournament.getActiveSeason();
        model.addAttribute("season", season);
        List<League> leagueList = season.getLeagueList();
        model.addAttribute("leagueList", leagueList);
        List<Cup> cupList = season.getCupList();
        model.addAttribute("cupList", cupList);
        List<Season> seasonList = tournament.getSeasonList().stream()
                .filter(s -> !s.getId().equals(season.getId()))
                .collect(Collectors.toList());
        model.addAttribute("seasonList", seasonList);
        Player player = playerRepository.findById(playerId).get();
        model.addAttribute("player", player);
        model.addAttribute("playerFirstNameTransliterated", player.getPlayerFirstNameTransliterated());
        model.addAttribute("playerLastNameTransliterated", player.getPlayerLastNameTransliterated());
        model.addAttribute("teamNameTransliterated", player.getTeam().getTeamNameTransliterated());
/*        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        model.addAttribute("league", leagueList.get(0));*/
        Cup cup = cupManagementRepository.findById(cupId).get();
        model.addAttribute("cup", cup);
        return "playerCup";
    }

/*    @GetMapping(value = "/admin/seasons")
    public String seasons(Model model) {
        List<Season> seasonList = (List<Season>) seasonRepository.findAll();
        model.addAttribute("seasonList", seasonList);
        return "AdminSeasonList";
    }*/

    @GetMapping(value = "/admin")
    public String admin(Model model) {
        List<Tournament> tournamentList = (List<Tournament>) tournamentRepository.findAll();
        model.addAttribute("tournamentList", tournamentList);
        return "AdminTournamentList";
    }
    @GetMapping(value = "/admin/tournaments/new")
    public String tournamentAdd(Model model) {
        return "AdminTournamentsCreate";
    }
    @GetMapping({"/admin/tournament/{id}"})
    public String tournamentList(Model model, @PathVariable("id") Long id) {
        Tournament tournament = tournamentRepository.findById(id).get();
        model.addAttribute("tournament", tournament);
        return "AdminTournament";
    }
    @GetMapping(value = "/admin/tournament/{id}/seasons/new")
    public String seasonAdd(Model model, @PathVariable("id") Long id) {
        Tournament tournament = tournamentRepository.findById(id).get();
        model.addAttribute("tournament", tournament);
        return "AdminSeasonsCreate";
    }
    @GetMapping({"/admin/tournament/{id}/seasons/active"})
    public String activeSeasons(Model model, @PathVariable("id") Long id) {
        Tournament tournament = tournamentRepository.findById(id).get();
        model.addAttribute("tournament", tournament);
        //List<Season> seasonList = tournament.getSeasonList();
        Season activeSeason = tournament.getActiveSeason();
        model.addAttribute("activeSeason", activeSeason);
        return "AdminActiveSeason";
    }
    @GetMapping({"/admin/tournament/{id}/seasons"})
    public String allSeasons(Model model, @PathVariable("id") Long id) {
        Tournament tournament = tournamentRepository.findById(id).get();
        model.addAttribute("tournament", tournament);
        //List<Season> seasonList = (List<Season>) seasonRepository.findAll();
        List<Season> seasonList = tournament.getSeasonList();
        model.addAttribute("seasonList", seasonList);
        return "AdminSeasonList";
    }
    @GetMapping(value = "/admin/tournament/{tournamentId}/season/{seasonId}/leaguelist")
    public String leagueList(Model model, @PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        List<League> leagueList = season.getLeagueList();
        //List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        model.addAttribute("leagueList", leagueList);
        return "AdminLeagueList";
    }
    @GetMapping(value = "/admin/tournament/{tournamentId}/season/{seasonId}/team/new")
    public String teamCreate(Model model, @PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        return "AdminTeamCreate";
    }
    @GetMapping(value = "/admin/tournament/{tournamentId}/season/{seasonId}/leaguelist/{leagueId}")
    public String league(Model model, @PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId, @PathVariable("leagueId") Long leagueId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        League league = leagueManagementRepository.findById(leagueId).get();
        model.addAttribute("league", league);
        return "AdminLeague";
    }
    @GetMapping({"/admin/tournament/{tournamentId}/season/{seasonId}/leaguelist/{leagueId}/team"})
    public String leagueTeamList(Model model, @PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId, @PathVariable("leagueId") Long leagueId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        League league = leagueManagementRepository.findById(leagueId).get();
        model.addAttribute("league", league);
        return "AdminSubmissionLeagueTeamList";
    }
    @GetMapping({"/admin/tournament/{tournamentId}/season/{seasonId}/leaguelist/{leagueId}/team/new"})
    public String leagueTeamListNewTeam(Model model, @PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId, @PathVariable("leagueId") Long leagueId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        League league = leagueManagementRepository.findById(leagueId).get();
        model.addAttribute("league", league);
        return "AdminSubmissionLeagueTeamListNewTeam";
    }
    @GetMapping({"/admin/tournament/{tournamentId}/season/{seasonId}/leaguelist/{leagueId}/tour"})
    public String leagueTourList(Model model, @PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId, @PathVariable("leagueId") Long leagueId, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        League league = leagueManagementRepository.findById(leagueId).get();
        model.addAttribute("league", league);
        return "AdminTourList";
    }
    @GetMapping(value = "/admin/tournament/{tournamentId}/season/{seasonId}/league/new")
    public String leagueNew(Model model,@PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        return "AdminLeagueNew";
    }
    @GetMapping(value = "/admin/tournament/{tournamentId}/season/{seasonId}/cuplist/{cupId}")
    public String cup(Model model, @PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId, @PathVariable("cupId") Long cupId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        Cup cup = cupManagementRepository.findById(cupId).get();
        model.addAttribute("cup", cup);
        return "AdminCup";
    }
    @GetMapping({"/admin/tournament/{tournamentId}/season/{seasonId}/cuplist/{cupId}/team"})
    public String cupTeamList(Model model, @PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId, @PathVariable("cupId") Long cupId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        Cup cup = cupManagementRepository.findById(cupId).get();
        model.addAttribute("cup", cup);
        return "AdminSubmissionCupTeamList";
    }
    @GetMapping({"/admin/tournament/{tournamentId}/season/{seasonId}/cuplist/{cupId}/tour"})
    public String cupTourList(Model model, @PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId, @PathVariable("cupId") Long cupId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        Cup cup = cupManagementRepository.findById(cupId).get();
        model.addAttribute("cup", cup);
        return "AdminCupTourList";
    }
    @GetMapping({"/admin/tournament/{tournamentId}/season/{seasonId}/cuplist/{cupId}/tour/details"})
    public String cupTourDetails(Model model, @PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId, @PathVariable("cupId") Long cupId, @RequestParam long uuid) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        //List<Cup> cupList = (List<Cup>) cupManagementRepository.findAll();
        List<Cup> cupList = season.getCupList();
        model.addAttribute("cupList", cupList);
        Cup cup = cupManagementRepository.findById(cupId).get();
        model.addAttribute("cup", cup);
        model.addAttribute("tour", tourRepository.findById(uuid).get());
        return "AdminCupTourDetails";
    }
    @GetMapping(value = "/admin/tournament/{tournamentId}/season/{seasonId}/cuplist")
    public String cupList(Model model, @PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        //List<Cup> cupList = (List<Cup>) cupManagementRepository.findAll();
        List<Cup> cupList = season.getCupList();
        model.addAttribute("cupList", cupList);
        return "AdminCupList";
    }
    @GetMapping(value = "/admin/tournament/{tournamentId}/season/{seasonId}/cuplist/new")
    public String cupNew(Model model, @PathVariable("tournamentId") Long tournamentId, @PathVariable("seasonId") Long seasonId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        model.addAttribute("tournament", tournament);
        Season season = seasonRepository.findById(seasonId).get();
        model.addAttribute("season", season);
        return "AdminCupNew";
    }
}
