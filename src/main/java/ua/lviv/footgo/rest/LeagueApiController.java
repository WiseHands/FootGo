package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.lviv.footgo.entity.*;
import ua.lviv.footgo.repository.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(path = "/api/season")
public class LeagueApiController {

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    LeagueManagementRepository leagueRepository;

    @Autowired
    TourRepository tourRepository;

    @Autowired
    GameRepository gameRepository;

    public static class LeagueCreateRequestBody {

        private String name;

        private String nameEn;

        private List<Long> teamList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNameEn() {
            return nameEn;
        }

        public void setNameEn(String nameEn) {
            this.nameEn = nameEn;
        }

        public List<Long> getTeamList() {
            return teamList;
        }

        public void setTeamList(List<Long> teamList) {
            this.teamList = teamList;
        }

        public LeagueCreateRequestBody() {

        }

    }
    public static final Integer NUMBER_OF_TOURS = 7;
    //public static final String GAME_TIME = "2019-09-20T09:00:00.000Z";
    public static final OffsetDateTime GAME_TIME = OffsetDateTime.of(2019, 9, 19, 0, 0, 0, 0, ZoneOffset.ofHours(+2));

    public Tour _createTour(Integer tourNumber) {
        Tour tour = new Tour();
        tour.setTourNumber(tourNumber);
        return tour;
    }

    public void _addGameToTour(Tour tour, Game game) {
        tour.addGame(game);
        game.setTour(tour);
    }

    public void _createGame(Team homeTeam, Team guestTeam, Tour tour) {
        Game game = new Game();
        Random random = new Random();

        game.setFirstTeam(homeTeam);
        game.setSecondTeam(guestTeam);
        game.setGameTime(GAME_TIME);
        _addGameToTour(tour, game);
        //_generateResult(game, homeTeam, guestTeam);
        //return game;
    }
    @PostMapping(value = "/{seasonId}/league", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public League create(@PathVariable Long seasonId, @RequestBody LeagueCreateRequestBody body) {
        Season season = seasonRepository.findById(seasonId).get();
        League league = new League();
        league.setName(body.name);
        league.setNameEn(body.nameEn);

        List<Team> teamList = new ArrayList<>();

        for(Long teamId : body.teamList) {
            Team team = teamRepository.findById(teamId).get();
            league.addTeam(team);

            teamList.add(team);

            System.out.println("TEAM NAME " + team.getTeamName());
        }

        if (teamList.size() <= 2 || teamList.size() >= 17 ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (teamList.size() == 3) {
            int NUMBER_OF_TOURS = 3;
            for (int i = 0; i < NUMBER_OF_TOURS; i++) {

                switch (i) {
                    case 0:
                        Tour tour = _createTour(i + 1);

                        _createGame(teamList.get(1), teamList.get(2), tour);

                        league.addTour(tour);
                        break;
                    case 1:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(0), teamList.get(1), tour);

                        league.addTour(tour);
                        break;
                    case 2:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(2), teamList.get(0), tour);

                        league.addTour(tour);
                        break;
                }
            }
        }

        if (teamList.size() == 4) {
            int NUMBER_OF_TOURS = 3;
            for (int i = 0; i < NUMBER_OF_TOURS; i++) {

                switch (i) {
                    case 0:
                        Tour tour = _createTour(i + 1);

                        _createGame(teamList.get(0), teamList.get(3), tour);
                        _createGame(teamList.get(1), teamList.get(2), tour);

                        league.addTour(tour);
                        break;
                    case 1:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(3), teamList.get(2), tour);
                        _createGame(teamList.get(0), teamList.get(1), tour);

                        league.addTour(tour);
                        break;
                    case 2:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(1), teamList.get(3), tour);
                        _createGame(teamList.get(2), teamList.get(0), tour);

                        league.addTour(tour);
                        break;
                }
            }
        }

        if (teamList.size() == 5) {
            int NUMBER_OF_TOURS = 5;
            for (int i = 0; i < NUMBER_OF_TOURS; i++) {

                switch (i) {
                    case 0:
                        Tour tour = _createTour(i + 1);

                        _createGame(teamList.get(1), teamList.get(4), tour);
                        _createGame(teamList.get(2), teamList.get(3), tour);

                        league.addTour(tour);
                        break;
                    case 1:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(4), teamList.get(2), tour);
                        _createGame(teamList.get(0), teamList.get(1), tour);

                        league.addTour(tour);
                        break;
                    case 2:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(2), teamList.get(0), tour);
                        _createGame(teamList.get(3), teamList.get(4), tour);

                        league.addTour(tour);
                        break;
                    case 3:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(0), teamList.get(3), tour);
                        _createGame(teamList.get(1), teamList.get(2), tour);

                        league.addTour(tour);
                        break;
                    case 4:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(3), teamList.get(1), tour);
                        _createGame(teamList.get(4), teamList.get(0), tour);

                        league.addTour(tour);
                        break;
                }
            }
        }

        if (teamList.size() == 6) {
            int NUMBER_OF_TOURS = 5;
            for (int i = 0; i < NUMBER_OF_TOURS; i++) {

                switch (i) {
                    case 0:
                        Tour tour = _createTour(i + 1);

                        _createGame(teamList.get(0), teamList.get(5), tour);
                        _createGame(teamList.get(1), teamList.get(4), tour);
                        _createGame(teamList.get(2), teamList.get(3), tour);

                        league.addTour(tour);
                        break;
                    case 1:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(5), teamList.get(3), tour);
                        _createGame(teamList.get(4), teamList.get(2), tour);
                        _createGame(teamList.get(0), teamList.get(1), tour);

                        league.addTour(tour);
                        break;
                    case 2:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(1), teamList.get(5), tour);
                        _createGame(teamList.get(2), teamList.get(0), tour);
                        _createGame(teamList.get(3), teamList.get(4), tour);

                        league.addTour(tour);
                        break;
                    case 3:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(5), teamList.get(4), tour);
                        _createGame(teamList.get(0), teamList.get(3), tour);
                        _createGame(teamList.get(1), teamList.get(2), tour);

                        league.addTour(tour);
                        break;
                    case 4:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(2), teamList.get(5), tour);
                        _createGame(teamList.get(3), teamList.get(1), tour);
                        _createGame(teamList.get(4), teamList.get(0), tour);

                        league.addTour(tour);
                        break;
                }
            }
        }

        if (teamList.size() == 7) {
            int NUMBER_OF_TOURS = 7;
            for (int i = 0; i < NUMBER_OF_TOURS; i++) {

                switch (i) {
                    case 0:
                        Tour tour = _createTour(i + 1);

                        _createGame(teamList.get(1), teamList.get(6), tour);
                        _createGame(teamList.get(2), teamList.get(5), tour);
                        _createGame(teamList.get(3), teamList.get(4), tour);

                        league.addTour(tour);
                        break;
                    case 1:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(5), teamList.get(4), tour);
                        _createGame(teamList.get(6), teamList.get(2), tour);
                        _createGame(teamList.get(0), teamList.get(1), tour);

                        league.addTour(tour);
                        break;
                    case 2:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(2), teamList.get(0), tour);
                        _createGame(teamList.get(3), teamList.get(6), tour);
                        _createGame(teamList.get(4), teamList.get(5), tour);

                        league.addTour(tour);
                        break;
                    case 3:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(6), teamList.get(4), tour);
                        _createGame(teamList.get(0), teamList.get(3), tour);
                        _createGame(teamList.get(1), teamList.get(2), tour);

                        league.addTour(tour);
                        break;
                    case 4:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(3), teamList.get(1), tour);
                        _createGame(teamList.get(4), teamList.get(0), tour);
                        _createGame(teamList.get(5), teamList.get(6), tour);

                        league.addTour(tour);
                        break;
                    case 5:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(0), teamList.get(5), tour);
                        _createGame(teamList.get(1), teamList.get(4), tour);
                        _createGame(teamList.get(2), teamList.get(3), tour);

                        league.addTour(tour);
                        break;
                    case 6:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(4), teamList.get(2), tour);
                        _createGame(teamList.get(5), teamList.get(1), tour);
                        _createGame(teamList.get(6), teamList.get(0), tour);

                        league.addTour(tour);
                        break;
                }
            }
        }

        if (teamList.size() == 8) {
            int NUMBER_OF_TOURS = 7;
            for (int i = 0; i < NUMBER_OF_TOURS; i++) {

                switch (i) {
                    case 0:
                        Tour tour = _createTour(i + 1);

                        _createGame(teamList.get(0), teamList.get(7), tour);
                        _createGame(teamList.get(1), teamList.get(6), tour);
                        _createGame(teamList.get(2), teamList.get(5), tour);
                        _createGame(teamList.get(3), teamList.get(4), tour);

                        league.addTour(tour);
                        break;
                    case 1:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(7), teamList.get(4), tour);
                        _createGame(teamList.get(5), teamList.get(4), tour);
                        _createGame(teamList.get(6), teamList.get(2), tour);
                        _createGame(teamList.get(0), teamList.get(1), tour);

                        league.addTour(tour);
                        break;
                    case 2:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(1), teamList.get(7), tour);
                        _createGame(teamList.get(2), teamList.get(0), tour);
                        _createGame(teamList.get(3), teamList.get(6), tour);
                        _createGame(teamList.get(4), teamList.get(5), tour);

                        league.addTour(tour);
                        break;
                    case 3:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(7), teamList.get(5), tour);
                        _createGame(teamList.get(6), teamList.get(4), tour);
                        _createGame(teamList.get(0), teamList.get(3), tour);
                        _createGame(teamList.get(1), teamList.get(2), tour);

                        league.addTour(tour);
                        break;
                    case 4:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(2), teamList.get(7), tour);
                        _createGame(teamList.get(3), teamList.get(1), tour);
                        _createGame(teamList.get(4), teamList.get(0), tour);
                        _createGame(teamList.get(5), teamList.get(6), tour);

                        league.addTour(tour);
                        break;
                    case 5:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(7), teamList.get(6), tour);
                        _createGame(teamList.get(0), teamList.get(5), tour);
                        _createGame(teamList.get(1), teamList.get(4), tour);
                        _createGame(teamList.get(2), teamList.get(3), tour);

                        league.addTour(tour);
                        break;
                    case 6:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(3), teamList.get(7), tour);
                        _createGame(teamList.get(4), teamList.get(2), tour);
                        _createGame(teamList.get(5), teamList.get(1), tour);
                        _createGame(teamList.get(6), teamList.get(0), tour);

                        league.addTour(tour);
                        break;
                }
            }
        }

        if (teamList.size() == 9) {
            int NUMBER_OF_TOURS = 9;
            for (int i = 0; i < NUMBER_OF_TOURS; i++) {

                switch (i) {
                    case 0:
                        Tour tour = _createTour(i + 1);

                        _createGame(teamList.get(1), teamList.get(8), tour);
                        _createGame(teamList.get(2), teamList.get(7), tour);
                        _createGame(teamList.get(3), teamList.get(6), tour);
                        _createGame(teamList.get(4), teamList.get(5), tour);

                        league.addTour(tour);
                        break;
                    case 1:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(6), teamList.get(4), tour);
                        _createGame(teamList.get(7), teamList.get(3), tour);
                        _createGame(teamList.get(8), teamList.get(2), tour);
                        _createGame(teamList.get(0), teamList.get(1), tour);

                        league.addTour(tour);
                        break;
                    case 2:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(2), teamList.get(0), tour);
                        _createGame(teamList.get(3), teamList.get(8), tour);
                        _createGame(teamList.get(4), teamList.get(7), tour);
                        _createGame(teamList.get(5), teamList.get(6), tour);

                        league.addTour(tour);
                        break;
                    case 3:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(7), teamList.get(5), tour);
                        _createGame(teamList.get(8), teamList.get(4), tour);
                        _createGame(teamList.get(0), teamList.get(3), tour);
                        _createGame(teamList.get(1), teamList.get(2), tour);

                        league.addTour(tour);
                        break;
                    case 4:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(3), teamList.get(1), tour);
                        _createGame(teamList.get(4), teamList.get(0), tour);
                        _createGame(teamList.get(5), teamList.get(8), tour);
                        _createGame(teamList.get(6), teamList.get(7), tour);

                        league.addTour(tour);
                        break;
                    case 5:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(8), teamList.get(6), tour);
                        _createGame(teamList.get(0), teamList.get(5), tour);
                        _createGame(teamList.get(1), teamList.get(4), tour);
                        _createGame(teamList.get(2), teamList.get(3), tour);

                        league.addTour(tour);
                        break;
                    case 6:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(4), teamList.get(2), tour);
                        _createGame(teamList.get(5), teamList.get(1), tour);
                        _createGame(teamList.get(6), teamList.get(0), tour);
                        _createGame(teamList.get(7), teamList.get(8), tour);

                        league.addTour(tour);
                        break;
                    case 7:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(0), teamList.get(7), tour);
                        _createGame(teamList.get(1), teamList.get(6), tour);
                        _createGame(teamList.get(2), teamList.get(5), tour);
                        _createGame(teamList.get(3), teamList.get(4), tour);

                        league.addTour(tour);
                        break;
                    case 8:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(5), teamList.get(3), tour);
                        _createGame(teamList.get(6), teamList.get(2), tour);
                        _createGame(teamList.get(7), teamList.get(1), tour);
                        _createGame(teamList.get(8), teamList.get(0), tour);

                        league.addTour(tour);
                        break;
                }
            }
        }

        if (teamList.size() == 10) {
            int NUMBER_OF_TOURS = 9;
            for (int i = 0; i < NUMBER_OF_TOURS; i++) {

                switch (i) {
                    case 0:
                        Tour tour = _createTour(i + 1);

                        _createGame(teamList.get(0), teamList.get(9), tour);
                        _createGame(teamList.get(1), teamList.get(8), tour);
                        _createGame(teamList.get(2), teamList.get(7), tour);
                        _createGame(teamList.get(3), teamList.get(6), tour);
                        _createGame(teamList.get(4), teamList.get(5), tour);

                        league.addTour(tour);
                        break;
                    case 1:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(9), teamList.get(5), tour);
                        _createGame(teamList.get(6), teamList.get(4), tour);
                        _createGame(teamList.get(7), teamList.get(3), tour);
                        _createGame(teamList.get(8), teamList.get(2), tour);
                        _createGame(teamList.get(0), teamList.get(1), tour);

                        league.addTour(tour);
                        break;
                    case 2:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(1), teamList.get(9), tour);
                        _createGame(teamList.get(2), teamList.get(0), tour);
                        _createGame(teamList.get(3), teamList.get(8), tour);
                        _createGame(teamList.get(4), teamList.get(7), tour);
                        _createGame(teamList.get(5), teamList.get(6), tour);

                        league.addTour(tour);
                        break;
                    case 3:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(9), teamList.get(6), tour);
                        _createGame(teamList.get(7), teamList.get(5), tour);
                        _createGame(teamList.get(8), teamList.get(4), tour);
                        _createGame(teamList.get(0), teamList.get(3), tour);
                        _createGame(teamList.get(1), teamList.get(2), tour);

                        league.addTour(tour);
                        break;
                    case 4:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(2), teamList.get(9), tour);
                        _createGame(teamList.get(3), teamList.get(1), tour);
                        _createGame(teamList.get(4), teamList.get(0), tour);
                        _createGame(teamList.get(5), teamList.get(8), tour);
                        _createGame(teamList.get(6), teamList.get(7), tour);

                        league.addTour(tour);
                        break;
                    case 5:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(9), teamList.get(7), tour);
                        _createGame(teamList.get(8), teamList.get(6), tour);
                        _createGame(teamList.get(0), teamList.get(5), tour);
                        _createGame(teamList.get(1), teamList.get(4), tour);
                        _createGame(teamList.get(2), teamList.get(3), tour);

                        league.addTour(tour);
                        break;
                    case 6:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(3), teamList.get(9), tour);
                        _createGame(teamList.get(4), teamList.get(2), tour);
                        _createGame(teamList.get(5), teamList.get(1), tour);
                        _createGame(teamList.get(6), teamList.get(0), tour);
                        _createGame(teamList.get(7), teamList.get(8), tour);

                        league.addTour(tour);
                        break;
                    case 7:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(9), teamList.get(8), tour);
                        _createGame(teamList.get(0), teamList.get(7), tour);
                        _createGame(teamList.get(1), teamList.get(6), tour);
                        _createGame(teamList.get(2), teamList.get(5), tour);
                        _createGame(teamList.get(3), teamList.get(4), tour);

                        league.addTour(tour);
                        break;
                    case 8:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(4), teamList.get(9), tour);
                        _createGame(teamList.get(5), teamList.get(3), tour);
                        _createGame(teamList.get(6), teamList.get(2), tour);
                        _createGame(teamList.get(7), teamList.get(1), tour);
                        _createGame(teamList.get(8), teamList.get(0), tour);

                        league.addTour(tour);
                        break;
                }
            }
        }

        if (teamList.size() == 11) {
            int NUMBER_OF_TOURS = 11;
            for (int i = 0; i < NUMBER_OF_TOURS; i++) {

                switch (i) {
                    case 0:
                        Tour tour = _createTour(i + 1);

                        _createGame(teamList.get(1), teamList.get(10), tour);
                        _createGame(teamList.get(2), teamList.get(9), tour);
                        _createGame(teamList.get(3), teamList.get(8), tour);
                        _createGame(teamList.get(4), teamList.get(7), tour);
                        _createGame(teamList.get(5), teamList.get(6), tour);

                        league.addTour(tour);
                        break;
                    case 1:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(7), teamList.get(5), tour);
                        _createGame(teamList.get(8), teamList.get(4), tour);
                        _createGame(teamList.get(9), teamList.get(3), tour);
                        _createGame(teamList.get(10), teamList.get(2), tour);
                        _createGame(teamList.get(0), teamList.get(1), tour);

                        league.addTour(tour);
                        break;
                    case 2:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(2), teamList.get(0), tour);
                        _createGame(teamList.get(3), teamList.get(10), tour);
                        _createGame(teamList.get(4), teamList.get(9), tour);
                        _createGame(teamList.get(5), teamList.get(8), tour);
                        _createGame(teamList.get(6), teamList.get(7), tour);

                        league.addTour(tour);
                        break;
                    case 3:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(8), teamList.get(6), tour);
                        _createGame(teamList.get(9), teamList.get(5), tour);
                        _createGame(teamList.get(10), teamList.get(4), tour);
                        _createGame(teamList.get(0), teamList.get(3), tour);
                        _createGame(teamList.get(1), teamList.get(2), tour);

                        league.addTour(tour);
                        break;
                    case 4:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(3), teamList.get(1), tour);
                        _createGame(teamList.get(4), teamList.get(0), tour);
                        _createGame(teamList.get(5), teamList.get(10), tour);
                        _createGame(teamList.get(6), teamList.get(9), tour);
                        _createGame(teamList.get(7), teamList.get(8), tour);

                        league.addTour(tour);
                        break;
                    case 5:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(9), teamList.get(7), tour);
                        _createGame(teamList.get(10), teamList.get(6), tour);
                        _createGame(teamList.get(0), teamList.get(5), tour);
                        _createGame(teamList.get(1), teamList.get(4), tour);
                        _createGame(teamList.get(2), teamList.get(3), tour);

                        league.addTour(tour);
                        break;
                    case 6:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(4), teamList.get(2), tour);
                        _createGame(teamList.get(5), teamList.get(1), tour);
                        _createGame(teamList.get(6), teamList.get(0), tour);
                        _createGame(teamList.get(7), teamList.get(10), tour);
                        _createGame(teamList.get(8), teamList.get(9), tour);

                        league.addTour(tour);
                        break;
                    case 7:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(10), teamList.get(8), tour);
                        _createGame(teamList.get(0), teamList.get(7), tour);
                        _createGame(teamList.get(1), teamList.get(6), tour);
                        _createGame(teamList.get(2), teamList.get(5), tour);
                        _createGame(teamList.get(3), teamList.get(4), tour);

                        league.addTour(tour);
                        break;
                    case 8:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(5), teamList.get(3), tour);
                        _createGame(teamList.get(6), teamList.get(2), tour);
                        _createGame(teamList.get(7), teamList.get(1), tour);
                        _createGame(teamList.get(8), teamList.get(0), tour);
                        _createGame(teamList.get(9), teamList.get(10), tour);

                        league.addTour(tour);
                        break;
                    case 9:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(0), teamList.get(9), tour);
                        _createGame(teamList.get(1), teamList.get(8), tour);
                        _createGame(teamList.get(2), teamList.get(7), tour);
                        _createGame(teamList.get(3), teamList.get(6), tour);
                        _createGame(teamList.get(4), teamList.get(5), tour);

                        league.addTour(tour);
                        break;
                    case 10:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(6), teamList.get(4), tour);
                        _createGame(teamList.get(7), teamList.get(3), tour);
                        _createGame(teamList.get(8), teamList.get(2), tour);
                        _createGame(teamList.get(9), teamList.get(1), tour);
                        _createGame(teamList.get(10), teamList.get(0), tour);

                        league.addTour(tour);
                        break;
                }
            }
        }

        if (teamList.size() == 12) {
            int NUMBER_OF_TOURS = 11;
            for (int i = 0; i < NUMBER_OF_TOURS; i++) {

                switch (i) {
                    case 0:
                        Tour tour = _createTour(i + 1);

                        _createGame(teamList.get(0), teamList.get(11), tour);
                        _createGame(teamList.get(1), teamList.get(10), tour);
                        _createGame(teamList.get(2), teamList.get(9), tour);
                        _createGame(teamList.get(3), teamList.get(8), tour);
                        _createGame(teamList.get(4), teamList.get(7), tour);
                        _createGame(teamList.get(5), teamList.get(6), tour);

                        league.addTour(tour);
                        break;
                    case 1:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(11), teamList.get(6), tour);
                        _createGame(teamList.get(7), teamList.get(5), tour);
                        _createGame(teamList.get(8), teamList.get(4), tour);
                        _createGame(teamList.get(9), teamList.get(3), tour);
                        _createGame(teamList.get(10), teamList.get(2), tour);
                        _createGame(teamList.get(0), teamList.get(1), tour);

                        league.addTour(tour);
                        break;
                    case 2:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(1), teamList.get(11), tour);
                        _createGame(teamList.get(2), teamList.get(0), tour);
                        _createGame(teamList.get(3), teamList.get(10), tour);
                        _createGame(teamList.get(4), teamList.get(9), tour);
                        _createGame(teamList.get(5), teamList.get(8), tour);
                        _createGame(teamList.get(6), teamList.get(7), tour);

                        league.addTour(tour);
                        break;
                    case 3:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(11), teamList.get(7), tour);
                        _createGame(teamList.get(8), teamList.get(6), tour);
                        _createGame(teamList.get(9), teamList.get(5), tour);
                        _createGame(teamList.get(10), teamList.get(4), tour);
                        _createGame(teamList.get(0), teamList.get(3), tour);
                        _createGame(teamList.get(1), teamList.get(2), tour);

                        league.addTour(tour);
                        break;
                    case 4:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(2), teamList.get(11), tour);
                        _createGame(teamList.get(3), teamList.get(1), tour);
                        _createGame(teamList.get(4), teamList.get(0), tour);
                        _createGame(teamList.get(5), teamList.get(10), tour);
                        _createGame(teamList.get(6), teamList.get(9), tour);
                        _createGame(teamList.get(7), teamList.get(8), tour);

                        league.addTour(tour);
                        break;
                    case 5:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(11), teamList.get(8), tour);
                        _createGame(teamList.get(9), teamList.get(7), tour);
                        _createGame(teamList.get(10), teamList.get(6), tour);
                        _createGame(teamList.get(0), teamList.get(5), tour);
                        _createGame(teamList.get(1), teamList.get(4), tour);
                        _createGame(teamList.get(2), teamList.get(3), tour);

                        league.addTour(tour);
                        break;
                    case 6:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(3), teamList.get(11), tour);
                        _createGame(teamList.get(4), teamList.get(2), tour);
                        _createGame(teamList.get(5), teamList.get(1), tour);
                        _createGame(teamList.get(6), teamList.get(0), tour);
                        _createGame(teamList.get(7), teamList.get(10), tour);
                        _createGame(teamList.get(8), teamList.get(9), tour);

                        league.addTour(tour);
                        break;
                    case 7:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(11), teamList.get(9), tour);
                        _createGame(teamList.get(10), teamList.get(8), tour);
                        _createGame(teamList.get(0), teamList.get(7), tour);
                        _createGame(teamList.get(1), teamList.get(6), tour);
                        _createGame(teamList.get(2), teamList.get(5), tour);
                        _createGame(teamList.get(3), teamList.get(4), tour);

                        league.addTour(tour);
                        break;
                    case 8:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(4), teamList.get(11), tour);
                        _createGame(teamList.get(5), teamList.get(3), tour);
                        _createGame(teamList.get(6), teamList.get(2), tour);
                        _createGame(teamList.get(7), teamList.get(1), tour);
                        _createGame(teamList.get(8), teamList.get(0), tour);
                        _createGame(teamList.get(9), teamList.get(10), tour);

                        league.addTour(tour);
                        break;
                    case 9:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(11), teamList.get(10), tour);
                        _createGame(teamList.get(0), teamList.get(9), tour);
                        _createGame(teamList.get(1), teamList.get(8), tour);
                        _createGame(teamList.get(2), teamList.get(7), tour);
                        _createGame(teamList.get(3), teamList.get(6), tour);
                        _createGame(teamList.get(4), teamList.get(5), tour);

                        league.addTour(tour);
                        break;
                    case 10:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(5), teamList.get(11), tour);
                        _createGame(teamList.get(6), teamList.get(4), tour);
                        _createGame(teamList.get(7), teamList.get(3), tour);
                        _createGame(teamList.get(8), teamList.get(2), tour);
                        _createGame(teamList.get(9), teamList.get(1), tour);
                        _createGame(teamList.get(10), teamList.get(0), tour);

                        league.addTour(tour);
                        break;
                }
            }
        }

        if (teamList.size() == 13) {
            int NUMBER_OF_TOURS = 13;
            for (int i = 0; i < NUMBER_OF_TOURS; i++) {

                switch (i) {
                    case 0:
                        Tour tour = _createTour(i + 1);

                        _createGame(teamList.get(1), teamList.get(12), tour);
                        _createGame(teamList.get(2), teamList.get(11), tour);
                        _createGame(teamList.get(3), teamList.get(10), tour);
                        _createGame(teamList.get(4), teamList.get(9), tour);
                        _createGame(teamList.get(5), teamList.get(8), tour);
                        _createGame(teamList.get(6), teamList.get(7), tour);

                        league.addTour(tour);
                        break;
                    case 1:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(8), teamList.get(6), tour);
                        _createGame(teamList.get(9), teamList.get(5), tour);
                        _createGame(teamList.get(10), teamList.get(4), tour);
                        _createGame(teamList.get(11), teamList.get(3), tour);
                        _createGame(teamList.get(12), teamList.get(2), tour);
                        _createGame(teamList.get(0), teamList.get(1), tour);

                        league.addTour(tour);
                        break;
                    case 2:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(2), teamList.get(0), tour);
                        _createGame(teamList.get(3), teamList.get(12), tour);
                        _createGame(teamList.get(4), teamList.get(11), tour);
                        _createGame(teamList.get(5), teamList.get(10), tour);
                        _createGame(teamList.get(6), teamList.get(9), tour);
                        _createGame(teamList.get(7), teamList.get(8), tour);

                        league.addTour(tour);
                        break;
                    case 3:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(9), teamList.get(7), tour);
                        _createGame(teamList.get(10), teamList.get(6), tour);
                        _createGame(teamList.get(11), teamList.get(5), tour);
                        _createGame(teamList.get(12), teamList.get(4), tour);
                        _createGame(teamList.get(0), teamList.get(3), tour);
                        _createGame(teamList.get(1), teamList.get(2), tour);

                        league.addTour(tour);
                        break;
                    case 4:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(3), teamList.get(1), tour);
                        _createGame(teamList.get(4), teamList.get(0), tour);
                        _createGame(teamList.get(5), teamList.get(12), tour);
                        _createGame(teamList.get(6), teamList.get(11), tour);
                        _createGame(teamList.get(7), teamList.get(10), tour);
                        _createGame(teamList.get(8), teamList.get(9), tour);

                        league.addTour(tour);
                        break;
                    case 5:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(10), teamList.get(8), tour);
                        _createGame(teamList.get(11), teamList.get(7), tour);
                        _createGame(teamList.get(12), teamList.get(6), tour);
                        _createGame(teamList.get(0), teamList.get(5), tour);
                        _createGame(teamList.get(1), teamList.get(4), tour);
                        _createGame(teamList.get(2), teamList.get(3), tour);

                        league.addTour(tour);
                        break;
                    case 6:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(4), teamList.get(2), tour);
                        _createGame(teamList.get(5), teamList.get(1), tour);
                        _createGame(teamList.get(6), teamList.get(0), tour);
                        _createGame(teamList.get(7), teamList.get(12), tour);
                        _createGame(teamList.get(8), teamList.get(11), tour);
                        _createGame(teamList.get(9), teamList.get(10), tour);

                        league.addTour(tour);
                        break;
                    case 7:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(11), teamList.get(9), tour);
                        _createGame(teamList.get(12), teamList.get(8), tour);
                        _createGame(teamList.get(0), teamList.get(7), tour);
                        _createGame(teamList.get(1), teamList.get(6), tour);
                        _createGame(teamList.get(2), teamList.get(5), tour);
                        _createGame(teamList.get(3), teamList.get(4), tour);

                        league.addTour(tour);
                        break;
                    case 8:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(5), teamList.get(3), tour);
                        _createGame(teamList.get(6), teamList.get(2), tour);
                        _createGame(teamList.get(7), teamList.get(1), tour);
                        _createGame(teamList.get(8), teamList.get(0), tour);
                        _createGame(teamList.get(9), teamList.get(12), tour);
                        _createGame(teamList.get(10), teamList.get(11), tour);

                        league.addTour(tour);
                        break;
                    case 9:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(12), teamList.get(10), tour);
                        _createGame(teamList.get(10), teamList.get(9), tour);
                        _createGame(teamList.get(1), teamList.get(8), tour);
                        _createGame(teamList.get(2), teamList.get(7), tour);
                        _createGame(teamList.get(3), teamList.get(6), tour);
                        _createGame(teamList.get(4), teamList.get(5), tour);

                        league.addTour(tour);
                        break;
                    case 10:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(6), teamList.get(4), tour);
                        _createGame(teamList.get(7), teamList.get(3), tour);
                        _createGame(teamList.get(8), teamList.get(2), tour);
                        _createGame(teamList.get(9), teamList.get(1), tour);
                        _createGame(teamList.get(10), teamList.get(0), tour);
                        _createGame(teamList.get(11), teamList.get(12), tour);

                        league.addTour(tour);
                        break;
                    case 11:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(0), teamList.get(11), tour);
                        _createGame(teamList.get(1), teamList.get(10), tour);
                        _createGame(teamList.get(2), teamList.get(9), tour);
                        _createGame(teamList.get(3), teamList.get(8), tour);
                        _createGame(teamList.get(4), teamList.get(7), tour);
                        _createGame(teamList.get(5), teamList.get(6), tour);

                        league.addTour(tour);
                        break;
                    case 12:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(7), teamList.get(5), tour);
                        _createGame(teamList.get(8), teamList.get(4), tour);
                        _createGame(teamList.get(9), teamList.get(3), tour);
                        _createGame(teamList.get(10), teamList.get(2), tour);
                        _createGame(teamList.get(11), teamList.get(1), tour);
                        _createGame(teamList.get(12), teamList.get(0), tour);

                        league.addTour(tour);
                        break;
                }
            }
        }

        if (teamList.size() == 14) {
            int NUMBER_OF_TOURS = 13;
            for (int i = 0; i < NUMBER_OF_TOURS; i++) {

                switch (i) {
                    case 0:
                        Tour tour = _createTour(i + 1);

                        _createGame(teamList.get(0), teamList.get(13), tour);
                        _createGame(teamList.get(1), teamList.get(12), tour);
                        _createGame(teamList.get(2), teamList.get(11), tour);
                        _createGame(teamList.get(3), teamList.get(10), tour);
                        _createGame(teamList.get(4), teamList.get(9), tour);
                        _createGame(teamList.get(5), teamList.get(8), tour);
                        _createGame(teamList.get(6), teamList.get(7), tour);

                        league.addTour(tour);
                        break;
                    case 1:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(13), teamList.get(7), tour);
                        _createGame(teamList.get(8), teamList.get(6), tour);
                        _createGame(teamList.get(9), teamList.get(5), tour);
                        _createGame(teamList.get(10), teamList.get(4), tour);
                        _createGame(teamList.get(11), teamList.get(3), tour);
                        _createGame(teamList.get(12), teamList.get(2), tour);
                        _createGame(teamList.get(0), teamList.get(1), tour);

                        league.addTour(tour);
                        break;
                    case 2:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(1), teamList.get(13), tour);
                        _createGame(teamList.get(2), teamList.get(0), tour);
                        _createGame(teamList.get(3), teamList.get(12), tour);
                        _createGame(teamList.get(4), teamList.get(11), tour);
                        _createGame(teamList.get(5), teamList.get(10), tour);
                        _createGame(teamList.get(6), teamList.get(9), tour);
                        _createGame(teamList.get(7), teamList.get(8), tour);

                        league.addTour(tour);
                        break;
                    case 3:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(13), teamList.get(8), tour);
                        _createGame(teamList.get(9), teamList.get(7), tour);
                        _createGame(teamList.get(10), teamList.get(6), tour);
                        _createGame(teamList.get(11), teamList.get(5), tour);
                        _createGame(teamList.get(12), teamList.get(4), tour);
                        _createGame(teamList.get(0), teamList.get(3), tour);
                        _createGame(teamList.get(1), teamList.get(2), tour);

                        league.addTour(tour);
                        break;
                    case 4:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(2), teamList.get(13), tour);
                        _createGame(teamList.get(3), teamList.get(1), tour);
                        _createGame(teamList.get(4), teamList.get(0), tour);
                        _createGame(teamList.get(5), teamList.get(12), tour);
                        _createGame(teamList.get(6), teamList.get(11), tour);
                        _createGame(teamList.get(7), teamList.get(10), tour);
                        _createGame(teamList.get(8), teamList.get(9), tour);

                        league.addTour(tour);
                        break;
                    case 5:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(13), teamList.get(9), tour);
                        _createGame(teamList.get(10), teamList.get(8), tour);
                        _createGame(teamList.get(11), teamList.get(7), tour);
                        _createGame(teamList.get(12), teamList.get(6), tour);
                        _createGame(teamList.get(0), teamList.get(5), tour);
                        _createGame(teamList.get(1), teamList.get(4), tour);
                        _createGame(teamList.get(2), teamList.get(3), tour);

                        league.addTour(tour);
                        break;
                    case 6:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(3), teamList.get(13), tour);
                        _createGame(teamList.get(4), teamList.get(2), tour);
                        _createGame(teamList.get(5), teamList.get(1), tour);
                        _createGame(teamList.get(6), teamList.get(0), tour);
                        _createGame(teamList.get(7), teamList.get(12), tour);
                        _createGame(teamList.get(8), teamList.get(11), tour);
                        _createGame(teamList.get(9), teamList.get(10), tour);

                        league.addTour(tour);
                        break;
                    case 7:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(13), teamList.get(10), tour);
                        _createGame(teamList.get(11), teamList.get(9), tour);
                        _createGame(teamList.get(12), teamList.get(8), tour);
                        _createGame(teamList.get(0), teamList.get(7), tour);
                        _createGame(teamList.get(1), teamList.get(6), tour);
                        _createGame(teamList.get(2), teamList.get(5), tour);
                        _createGame(teamList.get(3), teamList.get(4), tour);

                        league.addTour(tour);
                        break;
                    case 8:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(4), teamList.get(13), tour);
                        _createGame(teamList.get(5), teamList.get(3), tour);
                        _createGame(teamList.get(6), teamList.get(2), tour);
                        _createGame(teamList.get(7), teamList.get(1), tour);
                        _createGame(teamList.get(8), teamList.get(0), tour);
                        _createGame(teamList.get(9), teamList.get(12), tour);
                        _createGame(teamList.get(10), teamList.get(11), tour);

                        league.addTour(tour);
                        break;
                    case 9:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(13), teamList.get(11), tour);
                        _createGame(teamList.get(12), teamList.get(10), tour);
                        _createGame(teamList.get(10), teamList.get(9), tour);
                        _createGame(teamList.get(1), teamList.get(8), tour);
                        _createGame(teamList.get(2), teamList.get(7), tour);
                        _createGame(teamList.get(3), teamList.get(6), tour);
                        _createGame(teamList.get(4), teamList.get(5), tour);

                        league.addTour(tour);
                        break;
                    case 10:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(5), teamList.get(13), tour);
                        _createGame(teamList.get(6), teamList.get(4), tour);
                        _createGame(teamList.get(7), teamList.get(3), tour);
                        _createGame(teamList.get(8), teamList.get(2), tour);
                        _createGame(teamList.get(9), teamList.get(1), tour);
                        _createGame(teamList.get(10), teamList.get(0), tour);
                        _createGame(teamList.get(11), teamList.get(12), tour);

                        league.addTour(tour);
                        break;
                    case 11:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(13), teamList.get(12), tour);
                        _createGame(teamList.get(0), teamList.get(11), tour);
                        _createGame(teamList.get(1), teamList.get(10), tour);
                        _createGame(teamList.get(2), teamList.get(9), tour);
                        _createGame(teamList.get(3), teamList.get(8), tour);
                        _createGame(teamList.get(4), teamList.get(7), tour);
                        _createGame(teamList.get(5), teamList.get(6), tour);

                        league.addTour(tour);
                        break;
                    case 12:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(6), teamList.get(13), tour);
                        _createGame(teamList.get(7), teamList.get(5), tour);
                        _createGame(teamList.get(8), teamList.get(4), tour);
                        _createGame(teamList.get(9), teamList.get(3), tour);
                        _createGame(teamList.get(10), teamList.get(2), tour);
                        _createGame(teamList.get(11), teamList.get(1), tour);
                        _createGame(teamList.get(12), teamList.get(0), tour);

                        league.addTour(tour);
                        break;
                }
            }
        }

        if (teamList.size() == 15) {
            int NUMBER_OF_TOURS = 15;
            for (int i = 0; i < NUMBER_OF_TOURS; i++) {

                switch (i) {
                    case 0:
                        Tour tour = _createTour(i + 1);

                        _createGame(teamList.get(1), teamList.get(14), tour);
                        _createGame(teamList.get(2), teamList.get(13), tour);
                        _createGame(teamList.get(3), teamList.get(12), tour);
                        _createGame(teamList.get(4), teamList.get(11), tour);
                        _createGame(teamList.get(5), teamList.get(10), tour);
                        _createGame(teamList.get(6), teamList.get(9), tour);
                        _createGame(teamList.get(7), teamList.get(8), tour);

                        league.addTour(tour);
                        break;
                    case 1:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(9), teamList.get(7), tour);
                        _createGame(teamList.get(10), teamList.get(6), tour);
                        _createGame(teamList.get(11), teamList.get(5), tour);
                        _createGame(teamList.get(12), teamList.get(4), tour);
                        _createGame(teamList.get(13), teamList.get(3), tour);
                        _createGame(teamList.get(14), teamList.get(2), tour);
                        _createGame(teamList.get(0), teamList.get(1), tour);

                        league.addTour(tour);
                        break;
                    case 2:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(2), teamList.get(0), tour);
                        _createGame(teamList.get(3), teamList.get(14), tour);
                        _createGame(teamList.get(4), teamList.get(13), tour);
                        _createGame(teamList.get(5), teamList.get(12), tour);
                        _createGame(teamList.get(6), teamList.get(11), tour);
                        _createGame(teamList.get(7), teamList.get(10), tour);
                        _createGame(teamList.get(8), teamList.get(9), tour);

                        league.addTour(tour);
                        break;
                    case 3:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(10), teamList.get(8), tour);
                        _createGame(teamList.get(11), teamList.get(7), tour);
                        _createGame(teamList.get(12), teamList.get(6), tour);
                        _createGame(teamList.get(13), teamList.get(5), tour);
                        _createGame(teamList.get(14), teamList.get(4), tour);
                        _createGame(teamList.get(0), teamList.get(3), tour);
                        _createGame(teamList.get(1), teamList.get(2), tour);

                        league.addTour(tour);
                        break;
                    case 4:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(3), teamList.get(1), tour);
                        _createGame(teamList.get(4), teamList.get(0), tour);
                        _createGame(teamList.get(5), teamList.get(14), tour);
                        _createGame(teamList.get(6), teamList.get(13), tour);
                        _createGame(teamList.get(7), teamList.get(12), tour);
                        _createGame(teamList.get(8), teamList.get(11), tour);
                        _createGame(teamList.get(9), teamList.get(10), tour);

                        league.addTour(tour);
                        break;
                    case 5:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(11), teamList.get(9), tour);
                        _createGame(teamList.get(12), teamList.get(8), tour);
                        _createGame(teamList.get(13), teamList.get(7), tour);
                        _createGame(teamList.get(14), teamList.get(6), tour);
                        _createGame(teamList.get(0), teamList.get(5), tour);
                        _createGame(teamList.get(1), teamList.get(4), tour);
                        _createGame(teamList.get(2), teamList.get(3), tour);

                        league.addTour(tour);
                        break;
                    case 6:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(4), teamList.get(2), tour);
                        _createGame(teamList.get(5), teamList.get(1), tour);
                        _createGame(teamList.get(6), teamList.get(0), tour);
                        _createGame(teamList.get(7), teamList.get(14), tour);
                        _createGame(teamList.get(8), teamList.get(13), tour);
                        _createGame(teamList.get(9), teamList.get(12), tour);
                        _createGame(teamList.get(10), teamList.get(11), tour);

                        league.addTour(tour);
                        break;
                    case 7:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(12), teamList.get(10), tour);
                        _createGame(teamList.get(13), teamList.get(9), tour);
                        _createGame(teamList.get(14), teamList.get(8), tour);
                        _createGame(teamList.get(10), teamList.get(7), tour);
                        _createGame(teamList.get(1), teamList.get(6), tour);
                        _createGame(teamList.get(2), teamList.get(5), tour);
                        _createGame(teamList.get(3), teamList.get(4), tour);

                        league.addTour(tour);
                        break;
                    case 8:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(5), teamList.get(3), tour);
                        _createGame(teamList.get(6), teamList.get(2), tour);
                        _createGame(teamList.get(7), teamList.get(1), tour);
                        _createGame(teamList.get(8), teamList.get(0), tour);
                        _createGame(teamList.get(9), teamList.get(14), tour);
                        _createGame(teamList.get(10), teamList.get(13), tour);
                        _createGame(teamList.get(11), teamList.get(12), tour);

                        league.addTour(tour);
                        break;
                    case 9:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(13), teamList.get(11), tour);
                        _createGame(teamList.get(14), teamList.get(10), tour);
                        _createGame(teamList.get(0), teamList.get(9), tour);
                        _createGame(teamList.get(1), teamList.get(8), tour);
                        _createGame(teamList.get(2), teamList.get(7), tour);
                        _createGame(teamList.get(3), teamList.get(6), tour);
                        _createGame(teamList.get(4), teamList.get(5), tour);

                        league.addTour(tour);
                        break;
                    case 10:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(6), teamList.get(4), tour);
                        _createGame(teamList.get(7), teamList.get(3), tour);
                        _createGame(teamList.get(8), teamList.get(2), tour);
                        _createGame(teamList.get(9), teamList.get(1), tour);
                        _createGame(teamList.get(10), teamList.get(0), tour);
                        _createGame(teamList.get(11), teamList.get(14), tour);
                        _createGame(teamList.get(12), teamList.get(13), tour);

                        league.addTour(tour);
                        break;
                    case 11:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(14), teamList.get(12), tour);
                        _createGame(teamList.get(0), teamList.get(11), tour);
                        _createGame(teamList.get(1), teamList.get(10), tour);
                        _createGame(teamList.get(2), teamList.get(9), tour);
                        _createGame(teamList.get(3), teamList.get(8), tour);
                        _createGame(teamList.get(4), teamList.get(7), tour);
                        _createGame(teamList.get(5), teamList.get(6), tour);

                        league.addTour(tour);
                        break;
                    case 12:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(7), teamList.get(5), tour);
                        _createGame(teamList.get(8), teamList.get(4), tour);
                        _createGame(teamList.get(9), teamList.get(3), tour);
                        _createGame(teamList.get(10), teamList.get(2), tour);
                        _createGame(teamList.get(11), teamList.get(1), tour);
                        _createGame(teamList.get(12), teamList.get(0), tour);
                        _createGame(teamList.get(13), teamList.get(14), tour);

                        league.addTour(tour);
                        break;
                    case 13:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(0), teamList.get(13), tour);
                        _createGame(teamList.get(1), teamList.get(12), tour);
                        _createGame(teamList.get(2), teamList.get(11), tour);
                        _createGame(teamList.get(3), teamList.get(10), tour);
                        _createGame(teamList.get(4), teamList.get(9), tour);
                        _createGame(teamList.get(5), teamList.get(8), tour);
                        _createGame(teamList.get(6), teamList.get(7), tour);

                        league.addTour(tour);
                        break;
                    case 14:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(8), teamList.get(6), tour);
                        _createGame(teamList.get(9), teamList.get(5), tour);
                        _createGame(teamList.get(10), teamList.get(4), tour);
                        _createGame(teamList.get(11), teamList.get(3), tour);
                        _createGame(teamList.get(12), teamList.get(2), tour);
                        _createGame(teamList.get(13), teamList.get(1), tour);
                        _createGame(teamList.get(14), teamList.get(0), tour);

                        league.addTour(tour);
                        break;
                }
            }
        }

        if (teamList.size() == 16) {
            int NUMBER_OF_TOURS = 15;
            for (int i = 0; i < NUMBER_OF_TOURS; i++) {

                switch (i) {
                    case 0:
                        Tour tour = _createTour(i + 1);

                        _createGame(teamList.get(0), teamList.get(15), tour);
                        _createGame(teamList.get(1), teamList.get(14), tour);
                        _createGame(teamList.get(2), teamList.get(13), tour);
                        _createGame(teamList.get(3), teamList.get(12), tour);
                        _createGame(teamList.get(4), teamList.get(11), tour);
                        _createGame(teamList.get(5), teamList.get(10), tour);
                        _createGame(teamList.get(6), teamList.get(9), tour);
                        _createGame(teamList.get(7), teamList.get(8), tour);

                        league.addTour(tour);
                        break;
                    case 1:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(15), teamList.get(8), tour);
                        _createGame(teamList.get(9), teamList.get(7), tour);
                        _createGame(teamList.get(10), teamList.get(6), tour);
                        _createGame(teamList.get(11), teamList.get(5), tour);
                        _createGame(teamList.get(12), teamList.get(4), tour);
                        _createGame(teamList.get(13), teamList.get(3), tour);
                        _createGame(teamList.get(14), teamList.get(2), tour);
                        _createGame(teamList.get(0), teamList.get(1), tour);

                        league.addTour(tour);
                        break;
                    case 2:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(1), teamList.get(15), tour);
                        _createGame(teamList.get(2), teamList.get(0), tour);
                        _createGame(teamList.get(3), teamList.get(14), tour);
                        _createGame(teamList.get(4), teamList.get(13), tour);
                        _createGame(teamList.get(5), teamList.get(12), tour);
                        _createGame(teamList.get(6), teamList.get(11), tour);
                        _createGame(teamList.get(7), teamList.get(10), tour);
                        _createGame(teamList.get(8), teamList.get(9), tour);

                        league.addTour(tour);
                        break;
                    case 3:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(15), teamList.get(9), tour);
                        _createGame(teamList.get(10), teamList.get(8), tour);
                        _createGame(teamList.get(11), teamList.get(7), tour);
                        _createGame(teamList.get(12), teamList.get(6), tour);
                        _createGame(teamList.get(13), teamList.get(5), tour);
                        _createGame(teamList.get(14), teamList.get(4), tour);
                        _createGame(teamList.get(0), teamList.get(3), tour);
                        _createGame(teamList.get(1), teamList.get(2), tour);

                        league.addTour(tour);
                        break;
                    case 4:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(2), teamList.get(15), tour);
                        _createGame(teamList.get(3), teamList.get(1), tour);
                        _createGame(teamList.get(4), teamList.get(0), tour);
                        _createGame(teamList.get(5), teamList.get(14), tour);
                        _createGame(teamList.get(6), teamList.get(13), tour);
                        _createGame(teamList.get(7), teamList.get(12), tour);
                        _createGame(teamList.get(8), teamList.get(11), tour);
                        _createGame(teamList.get(9), teamList.get(10), tour);

                        league.addTour(tour);
                        break;
                    case 5:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(15), teamList.get(10), tour);
                        _createGame(teamList.get(11), teamList.get(9), tour);
                        _createGame(teamList.get(12), teamList.get(8), tour);
                        _createGame(teamList.get(13), teamList.get(7), tour);
                        _createGame(teamList.get(14), teamList.get(6), tour);
                        _createGame(teamList.get(0), teamList.get(5), tour);
                        _createGame(teamList.get(1), teamList.get(4), tour);
                        _createGame(teamList.get(2), teamList.get(3), tour);

                        league.addTour(tour);
                        break;
                    case 6:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(3), teamList.get(15), tour);
                        _createGame(teamList.get(4), teamList.get(2), tour);
                        _createGame(teamList.get(5), teamList.get(1), tour);
                        _createGame(teamList.get(6), teamList.get(0), tour);
                        _createGame(teamList.get(7), teamList.get(14), tour);
                        _createGame(teamList.get(8), teamList.get(13), tour);
                        _createGame(teamList.get(9), teamList.get(12), tour);
                        _createGame(teamList.get(10), teamList.get(11), tour);

                        league.addTour(tour);
                        break;
                    case 7:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(15), teamList.get(11), tour);
                        _createGame(teamList.get(12), teamList.get(10), tour);
                        _createGame(teamList.get(13), teamList.get(9), tour);
                        _createGame(teamList.get(14), teamList.get(8), tour);
                        _createGame(teamList.get(10), teamList.get(7), tour);
                        _createGame(teamList.get(1), teamList.get(6), tour);
                        _createGame(teamList.get(2), teamList.get(5), tour);
                        _createGame(teamList.get(3), teamList.get(4), tour);

                        league.addTour(tour);
                        break;
                    case 8:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(4), teamList.get(15), tour);
                        _createGame(teamList.get(5), teamList.get(3), tour);
                        _createGame(teamList.get(6), teamList.get(2), tour);
                        _createGame(teamList.get(7), teamList.get(1), tour);
                        _createGame(teamList.get(8), teamList.get(0), tour);
                        _createGame(teamList.get(9), teamList.get(14), tour);
                        _createGame(teamList.get(10), teamList.get(13), tour);
                        _createGame(teamList.get(11), teamList.get(12), tour);

                        league.addTour(tour);
                        break;
                    case 9:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(15), teamList.get(12), tour);
                        _createGame(teamList.get(13), teamList.get(11), tour);
                        _createGame(teamList.get(14), teamList.get(10), tour);
                        _createGame(teamList.get(0), teamList.get(9), tour);
                        _createGame(teamList.get(1), teamList.get(8), tour);
                        _createGame(teamList.get(2), teamList.get(7), tour);
                        _createGame(teamList.get(3), teamList.get(6), tour);
                        _createGame(teamList.get(4), teamList.get(5), tour);

                        league.addTour(tour);
                        break;
                    case 10:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(5), teamList.get(15), tour);
                        _createGame(teamList.get(6), teamList.get(4), tour);
                        _createGame(teamList.get(7), teamList.get(3), tour);
                        _createGame(teamList.get(8), teamList.get(2), tour);
                        _createGame(teamList.get(9), teamList.get(1), tour);
                        _createGame(teamList.get(10), teamList.get(0), tour);
                        _createGame(teamList.get(11), teamList.get(14), tour);
                        _createGame(teamList.get(12), teamList.get(13), tour);

                        league.addTour(tour);
                        break;
                    case 11:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(15), teamList.get(13), tour);
                        _createGame(teamList.get(14), teamList.get(12), tour);
                        _createGame(teamList.get(0), teamList.get(11), tour);
                        _createGame(teamList.get(1), teamList.get(10), tour);
                        _createGame(teamList.get(2), teamList.get(9), tour);
                        _createGame(teamList.get(3), teamList.get(8), tour);
                        _createGame(teamList.get(4), teamList.get(7), tour);
                        _createGame(teamList.get(5), teamList.get(6), tour);

                        league.addTour(tour);
                        break;
                    case 12:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(6), teamList.get(15), tour);
                        _createGame(teamList.get(7), teamList.get(5), tour);
                        _createGame(teamList.get(8), teamList.get(4), tour);
                        _createGame(teamList.get(9), teamList.get(3), tour);
                        _createGame(teamList.get(10), teamList.get(2), tour);
                        _createGame(teamList.get(11), teamList.get(1), tour);
                        _createGame(teamList.get(12), teamList.get(0), tour);
                        _createGame(teamList.get(13), teamList.get(14), tour);

                        league.addTour(tour);
                        break;
                    case 13:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(15), teamList.get(14), tour);
                        _createGame(teamList.get(0), teamList.get(13), tour);
                        _createGame(teamList.get(1), teamList.get(12), tour);
                        _createGame(teamList.get(2), teamList.get(11), tour);
                        _createGame(teamList.get(3), teamList.get(10), tour);
                        _createGame(teamList.get(4), teamList.get(9), tour);
                        _createGame(teamList.get(5), teamList.get(8), tour);
                        _createGame(teamList.get(6), teamList.get(7), tour);

                        league.addTour(tour);
                        break;
                    case 14:
                        tour = _createTour(i + 1);

                        _createGame(teamList.get(7), teamList.get(15), tour);
                        _createGame(teamList.get(8), teamList.get(6), tour);
                        _createGame(teamList.get(9), teamList.get(5), tour);
                        _createGame(teamList.get(10), teamList.get(4), tour);
                        _createGame(teamList.get(11), teamList.get(3), tour);
                        _createGame(teamList.get(12), teamList.get(2), tour);
                        _createGame(teamList.get(13), teamList.get(1), tour);
                        _createGame(teamList.get(14), teamList.get(0), tour);

                        league.addTour(tour);
                        break;
                }
            }
        }

        league = leagueRepository.save(league);
        season.addLeague(league);
        seasonRepository.save(season);
        return league;

    }

    @PostMapping(value = "/{seasonId}/leaguelist/{leagueId}/tour/details", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void createNewGameInTour(@PathVariable Long seasonId, @PathVariable Long leagueId, @RequestParam Long tourId) {
        Season season = seasonRepository.findById(seasonId).get();
        League league = leagueRepository.findById(leagueId).get();
        Tour tour = tourRepository.findById(tourId).get();
        _createGame(null, null, tour);

        leagueRepository.save(league);
    }

    @DeleteMapping(value = "/{seasonId}/leaguelist/{leagueId}/game/{gameId}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void removeGameFromTour(@PathVariable Long seasonId, @PathVariable Long leagueId, @RequestParam Long tourId, @RequestParam Long gameId) {
        Season season = seasonRepository.findById(seasonId).get();
        League league = leagueRepository.findById(leagueId).get();
        Tour tour = tourRepository.findById(tourId).get();
        Game game = gameRepository.findById(gameId).get();
        tour.removeGame(game);
        game.getTeamAGoals().clear();
        game.getTeamBGoals().clear();
        game.getTeamACards().clear();
        game.getTeamBCards().clear();
        gameRepository.deleteById(gameId);

        leagueRepository.save(league);
    }

}
