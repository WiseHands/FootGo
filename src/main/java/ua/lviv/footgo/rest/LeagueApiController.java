package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.lviv.footgo.entity.*;
import ua.lviv.footgo.repository.LeagueManagementRepository;
import ua.lviv.footgo.repository.SeasonRepository;
import ua.lviv.footgo.repository.TeamRepository;
import ua.lviv.footgo.repository.TourRepository;

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
                        break;                }
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
                        break;                }
            }
        }

        league = leagueRepository.save(league);
        season.addLeague(league);
        seasonRepository.save(season);
        return league;

    }
}
