package ua.lviv.footgo.util;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.lviv.footgo.entity.*;
import ua.lviv.footgo.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ResultsGenerator {

    public static final Integer NUMBER_OF_TEAMS = 10;
    public static final Integer NUMBER_OF_PLAYERS_IN_TEAM = 25;
    public static final Integer NUMBER_OF_TOURS = 9;
    public static final Integer NUMBER_OF_GAMES_IN_TOUR = 5;
    public static final String GAME_TIME = "2019-09-20T09:00:00.000Z";


    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private CaptainRepository captainRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private  LeagueManagementRepository leagueManagementRepository;

    private Faker faker = new Faker();

    public Captain _createCaptain(Team team) {
        Captain captain = new Captain();
        captain.setCaptainName(faker.name().name());
        captain.setCaptainEmail(captain.getCaptainName() + "@gmail.com");
        captain.setCaptainPhone(faker.phoneNumber().phoneNumber());

        captain.setTeam(team);
        team.setCaptain(captain);

        return captain;
    }

    public void _createPlayerList(Team team) {
        for(int i=0; i<NUMBER_OF_PLAYERS_IN_TEAM; i++) {
            _createPlayer(team, team.getTeamName() + " " + i);
        }
    }

    public void _createPlayer(Team team, String name) {
        Player player = new Player();
        player.setFirstName(faker.name().firstName());
        player.setLastName(faker.name().lastName());
        player.setTeam(team);
        team.addPlayer(player);
    }

    public Goal _createGoal(Game game, Player player, Integer time) {
        Goal goal = new Goal();
        goal.setTime(time);
        goal.setPlayer(player);
        goal.setGame(game);
        return goal;
    }

    public Tour _createTour(Integer tourNumber) {
        Tour tour = new Tour();
        tour.setTourNumber(tourNumber);
        return tour;
    }

    public void _addGameToTour(Tour tour, Game game) {
        tour.addGame(game);
        game.setTour(tour);
    }

    public Game _createGame(Team homeTeam, Team guestTeam, Tour tour) {
        Game game = new Game();
        Random random = new Random();

        game.setFirstTeam(homeTeam);
        game.setSecondTeam(guestTeam);
        game.setGameTime(GAME_TIME);
        _addGameToTour(tour, game);
        _generateResult(game, homeTeam, guestTeam);
        return game;
    }

    public void _generateResult(Game game, Team homeTeam, Team guestTeam) {
        Random random = new Random();
        System.out.println("\n\n\n Game " + game.getGameTime());


        for(int i=0; i<random.nextInt(5); i++) {
            _addGoal(game, homeTeam, guestTeam);
        }



    }

    private void  _addGoal(Game game, Team homeTeam, Team guestTeam) {
        Random random = new Random();
        Boolean addGoalToHomeTeam = random.nextBoolean();

        if(addGoalToHomeTeam) {
            Integer playerToScore = random.nextInt(homeTeam.getPlayers().size());
            Player player = homeTeam.getPlayers().get(playerToScore);

            Goal goal = new Goal();
            goal.setTime(random.nextInt(91));
            goal.setPlayer(player);

            player = playerRepository.save(player);

            goal.setGame(game);
            game.addGoalForFirstTeam(goal);
        } else {
            Integer playerToScore = random.nextInt(guestTeam.getPlayers().size());
            Player player = guestTeam.getPlayers().get(playerToScore);

            Goal goal = new Goal();
            goal.setTime(random.nextInt(91));
            goal.setPlayer(player);

            player = playerRepository.save(player);

            goal.setGame(game);
            game.addGoalForSecondTeam(goal);
        }
    }


    public Team _createTeam(String name) {
        Team team = new Team();
        team.setTeamName(name);
        team = teamRepository.save(team);
        Captain captain = _createCaptain(team);
        captainRepository.save(captain);
        _createPlayerList(team);
        teamRepository.save(team);
        return team;
    }

    public void _createLeague() {
        List<Team> teamList = new ArrayList<>();

        for(int i=0; i<NUMBER_OF_TEAMS; i++) {
            Team team = _createTeam("TEAM " + (1 + i));
            teamList.add(team);
        }


        for(int i=0; i<NUMBER_OF_TOURS; i++) {

            switch (Integer.valueOf(i)) {
                case 0:
                    Tour tour = _createTour(i+1);

                    _createGame(teamList.get(0), teamList.get(9), tour);
                    _createGame(teamList.get(1), teamList.get(8), tour);
                    _createGame(teamList.get(2), teamList.get(7), tour);
                    _createGame(teamList.get(3), teamList.get(6), tour);
                    _createGame(teamList.get(4), teamList.get(5), tour);

                    tourRepository.save(tour);
                    break;
                case 1:
                    tour = _createTour(i+1);

                    _createGame(teamList.get(9), teamList.get(5), tour);
                    _createGame(teamList.get(6), teamList.get(4), tour);
                    _createGame(teamList.get(7), teamList.get(3), tour);
                    _createGame(teamList.get(8), teamList.get(2), tour);
                    _createGame(teamList.get(0), teamList.get(1), tour);

                    tourRepository.save(tour);
                    break;
                case 2:
                    tour = _createTour(i+1);

                    _createGame(teamList.get(1), teamList.get(9), tour);
                    _createGame(teamList.get(2), teamList.get(0), tour);
                    _createGame(teamList.get(3), teamList.get(8), tour);
                    _createGame(teamList.get(4), teamList.get(7), tour);
                    _createGame(teamList.get(5), teamList.get(6), tour);

                    tourRepository.save(tour);
                    break;
                case 3:
                    tour = _createTour(i+1);


                    _createGame(teamList.get(9), teamList.get(6), tour);
                    _createGame(teamList.get(7), teamList.get(5), tour);
                    _createGame(teamList.get(8), teamList.get(4), tour);
                    _createGame(teamList.get(0), teamList.get(3), tour);
                    _createGame(teamList.get(1), teamList.get(2), tour);

                    tourRepository.save(tour);
                    break;
                case 4:
                    tour = _createTour(i+1);

                    _createGame(teamList.get(2), teamList.get(9), tour);
                    _createGame(teamList.get(3), teamList.get(1), tour);
                    _createGame(teamList.get(4), teamList.get(0), tour);
                    _createGame(teamList.get(5), teamList.get(8), tour);
                    _createGame(teamList.get(6), teamList.get(7), tour);

                    tourRepository.save(tour);
                    break;
                case 5:
                    tour = _createTour(i+1);

                    _createGame(teamList.get(9), teamList.get(7), tour);
                    _createGame(teamList.get(8), teamList.get(6), tour);
                    _createGame(teamList.get(0), teamList.get(5), tour);
                    _createGame(teamList.get(1), teamList.get(4), tour);
                    _createGame(teamList.get(2), teamList.get(3), tour);

                    tourRepository.save(tour);
                    break;
                case 6:
                    tour = _createTour(i+1);

                    _createGame(teamList.get(3), teamList.get(9), tour);
                    _createGame(teamList.get(4), teamList.get(2), tour);
                    _createGame(teamList.get(5), teamList.get(1), tour);
                    _createGame(teamList.get(6), teamList.get(0), tour);
                    _createGame(teamList.get(7), teamList.get(8), tour);

                    tourRepository.save(tour);
                    break;
                case 7:
                    tour = _createTour(i+1);

                    _createGame(teamList.get(9), teamList.get(8), tour);
                    _createGame(teamList.get(0), teamList.get(7), tour);
                    _createGame(teamList.get(1), teamList.get(6), tour);
                    _createGame(teamList.get(2), teamList.get(5), tour);
                    _createGame(teamList.get(3), teamList.get(4), tour);

                    tourRepository.save(tour);
                    break;
                case 8:
                    tour = _createTour(i+1);

                    _createGame(teamList.get(4), teamList.get(9), tour);
                    _createGame(teamList.get(5), teamList.get(3), tour);
                    _createGame(teamList.get(6), teamList.get(2), tour);
                    _createGame(teamList.get(7), teamList.get(1), tour);
                    _createGame(teamList.get(8), teamList.get(0), tour);

                    tourRepository.save(tour);
                    break;
            }
        }
    }

    public League _createLeagueStatusIfDoesntExist() {
        League league = new League();
        league.setIsSubmissionOpened(true);
        List<Tour> tourList = (List<Tour>) tourRepository.findAll();
        for(Tour tour : tourList) {
            tour.setLeague(league);
        }
        leagueManagementRepository.save(league);

        return league;
    }


}
