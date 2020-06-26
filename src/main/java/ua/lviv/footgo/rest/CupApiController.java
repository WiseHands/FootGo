package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.lviv.footgo.entity.*;
import ua.lviv.footgo.repository.*;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

@RestController
@RequestMapping(path = "/api/season")
public class CupApiController {

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    LeagueManagementRepository leagueRepository;

    @Autowired
    CupManagementRepository cupRepository;

    @Autowired
    TourRepository tourRepository;

    public static class CupCreateRequestBody {

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

        public CupCreateRequestBody() {

        }

    }
    public static final Integer NUMBER_OF_TOURS = 4;
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

        game.setFirstTeam(homeTeam);
        game.setSecondTeam(guestTeam);
        game.setGameTime(GAME_TIME);
        _addGameToTour(tour, game);
    }
    @PostMapping(value = "/{seasonId}/cup", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Cup create(@PathVariable Long seasonId, @RequestBody CupCreateRequestBody body) {
        Season season = seasonRepository.findById(seasonId).get();
        Cup cup = new Cup();
        cup.setName(body.name);
        cup.setNameEn(body.nameEn);
        System.out.println(body.name);
        System.out.println(body.nameEn);

        List<Team> teamList = new ArrayList<>();

        for(Long teamId : body.teamList) {
            Team team = teamRepository.findById(teamId).get();
            cup.addTeam(team);

            teamList.add(team);

            System.out.println(team.getTeamName());
        }

        if (teamList.size() <= 3 || teamList.size() >= 33 ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (teamList.size() == 4) {
            int NUMBER_OF_TOURS = 2;
            for (int i = 0; i < NUMBER_OF_TOURS; i++) {

                switch (i) {
                    case 0:
                        Tour tour = _createTour(i + 1);

                        _createGame(teamList.get(0), teamList.get(3), tour);
                        _createGame(teamList.get(1), teamList.get(2), tour);

                        cup.addTour(tour);
                        break;
                    case 1:
                        tour = _createTour(i + 1);

                        _createGame(null, null, tour);

                        cup.addTour(tour);
                        break;
                }
            }
        }

        if (teamList.size() == 8) {
            int NUMBER_OF_TOURS = 3;
            for (int i = 0; i < NUMBER_OF_TOURS; i++) {

                switch (i) {
                    case 0:
                        Tour tour = _createTour(i + 1);

                        _createGame(teamList.get(0), teamList.get(7), tour);
                        _createGame(teamList.get(1), teamList.get(6), tour);
                        _createGame(teamList.get(2), teamList.get(5), tour);
                        _createGame(teamList.get(3), teamList.get(4), tour);

                        cup.addTour(tour);
                        break;
                    case 1:
                        tour = _createTour(i + 1);

                        _createGame(null, null, tour);
                        _createGame(null, null, tour);

                        cup.addTour(tour);
                        break;
                    case 2:
                        tour = _createTour(i + 1);

                        _createGame(null, null, tour);

                        cup.addTour(tour);
                        break;
                }
            }
        }

        if (teamList.size() == 16) {
            int NUMBER_OF_TOURS = 4;
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

                        cup.addTour(tour);
                        break;
                    case 1:
                        tour = _createTour(i + 1);

                        _createGame(null, null, tour);
                        _createGame(null, null, tour);
                        _createGame(null, null, tour);
                        _createGame(null, null, tour);

                        cup.addTour(tour);
                        break;
                    case 2:
                        tour = _createTour(i + 1);

                        _createGame(null, null, tour);
                        _createGame(null, null, tour);

                        cup.addTour(tour);
                        break;
                    case 3:
                        tour = _createTour(i + 1);

                        _createGame(null, null, tour);

                        cup.addTour(tour);
                        break;

                }
            }
        }

        if (teamList.size() == 32) {
            int NUMBER_OF_TOURS = 5;
            for (int i = 0; i < NUMBER_OF_TOURS; i++) {

                switch (i) {
                    case 0:
                        Tour tour = _createTour(i + 1);

                        _createGame(teamList.get(0), teamList.get(31), tour);
                        _createGame(teamList.get(1), teamList.get(30), tour);
                        _createGame(teamList.get(2), teamList.get(29), tour);
                        _createGame(teamList.get(3), teamList.get(28), tour);
                        _createGame(teamList.get(4), teamList.get(27), tour);
                        _createGame(teamList.get(5), teamList.get(26), tour);
                        _createGame(teamList.get(6), teamList.get(25), tour);
                        _createGame(teamList.get(7), teamList.get(24), tour);
                        _createGame(teamList.get(8), teamList.get(23), tour);
                        _createGame(teamList.get(9), teamList.get(22), tour);
                        _createGame(teamList.get(10), teamList.get(21), tour);
                        _createGame(teamList.get(11), teamList.get(20), tour);
                        _createGame(teamList.get(12), teamList.get(19), tour);
                        _createGame(teamList.get(13), teamList.get(18), tour);
                        _createGame(teamList.get(14), teamList.get(17), tour);
                        _createGame(teamList.get(15), teamList.get(16), tour);

                        cup.addTour(tour);
                        break;
                    case 1:
                        tour = _createTour(i + 1);

                        _createGame(null, null, tour);
                        _createGame(null, null, tour);
                        _createGame(null, null, tour);
                        _createGame(null, null, tour);
                        _createGame(null, null, tour);
                        _createGame(null, null, tour);
                        _createGame(null, null, tour);
                        _createGame(null, null, tour);

                        cup.addTour(tour);
                        break;
                    case 2:
                        tour = _createTour(i + 1);

                        _createGame(null, null, tour);
                        _createGame(null, null, tour);
                        _createGame(null, null, tour);
                        _createGame(null, null, tour);

                        cup.addTour(tour);
                        break;
                    case 3:
                        tour = _createTour(i + 1);

                        _createGame(null, null, tour);
                        _createGame(null, null, tour);

                        cup.addTour(tour);
                        break;
                    case 4:
                        tour = _createTour(i + 1);

                        _createGame(null, null, tour);

                        cup.addTour(tour);
                        break;
                }
            }
        }

        cup = cupRepository.save(cup);
        season.addCup(cup);
        seasonRepository.save(season);
        return cup;

    }
}
