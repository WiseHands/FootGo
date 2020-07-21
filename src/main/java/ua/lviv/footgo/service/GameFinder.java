package ua.lviv.footgo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ua.lviv.footgo.entity.Game;
import ua.lviv.footgo.entity.League;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.entity.Tour;
import ua.lviv.footgo.repository.GameRepository;
import ua.lviv.footgo.repository.LeagueManagementRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameFinder {

    @Autowired
    LeagueManagementRepository leagueManagementRepository;

    @Autowired
    GameRepository gameRepository;

    public List<Game> findAllGamesForTeam(Team team) {
        List<Game> gameList = (List<Game>) gameRepository.findByFirstTeam(team);
        gameList.addAll((List<Game>) gameRepository.findBySecondTeam(team));

        Collections.sort(gameList, new Comparator<Game>() {
            public int compare(Game o1, Game o2) {
                return o1.getTour().getTourNumber().compareTo(o2.getTour().getTourNumber());
            }
        });

        return gameList;
    }

    public List<Game> findAllCompletedGamesForTeam(Team team){
        List<Game> gameList = (List<Game>) gameRepository.findByFirstTeamAndIsCompleted(team, true);
        gameList.addAll((List<Game>) gameRepository.findBySecondTeamAndIsCompleted(team, true));

        Collections.sort(gameList, new Comparator<Game>() {
            public int compare(Game o1, Game o2) {
                return o1.getTour().getTourNumber().compareTo(o2.getTour().getTourNumber());
            }
        });

        return gameList;
    }

    public List<Game> findAllCompletedGamesForTeamByLeague(Long leagueId, Team team){
        League league = leagueManagementRepository.findById(leagueId).get();
        List<Tour> tourList = league.getTours();

        List<Game> gameList = new ArrayList<>();

        List<Game> finalGameList_ = gameList;
        tourList.forEach(tour -> {
            List<Game> _gameList = tour.getGameList();
            finalGameList_.addAll(_gameList);

        });

        gameList = gameList.stream().filter(Game::isCompleted).collect(Collectors.toList());

        System.out.println("GAME_LIST " + gameList);

        //List<Game> gameList = (List<Game>) gameRepository.findByFirstTeamAndIsCompleted(team, true);
        //gameList.addAll((List<Game>) gameRepository.findBySecondTeamAndIsCompleted(team, true));

        Collections.sort(gameList, new Comparator<Game>() {
            public int compare(Game o1, Game o2) {
                return o1.getTour().getTourNumber().compareTo(o2.getTour().getTourNumber());
            }
        });

        return gameList;
    }
}
