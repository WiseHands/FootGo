package ua.lviv.footgo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.footgo.entity.*;
import ua.lviv.footgo.jsonmapper.PlayerGoals;
import ua.lviv.footgo.jsonmapper.TeamResults;
import ua.lviv.footgo.repository.GameRepository;
import ua.lviv.footgo.repository.GoalRepository;
import ua.lviv.footgo.repository.LeagueManagementRepository;
import ua.lviv.footgo.repository.TeamRepository;

import java.util.*;

@Service
public class TopScorerService {

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    LeagueManagementRepository leagueManagementRepository;

    @Autowired
    GameRepository gameRepository;


    public List<PlayerGoals> getResults() {
        Map<Player, PlayerGoals> playGoalMap = new HashMap<>();
        List<Goal> goalList = (List<Goal>) goalRepository.findAll();
        for(Goal _goal : goalList) {
            if(_goal.getGame() == null || !_goal.getGame().isCompleted() || _goal.getGame().isTeamAHasTechnicalDefeat() || _goal.getGame().isTeamBHasTechnicalDefeat()) {
                continue;
            }


            Player _player = _goal.getPlayer();
            PlayerGoals _playerGoals = playGoalMap.get(_player);
            if(_playerGoals == null) {
                _playerGoals = new PlayerGoals();
                _playerGoals.setPlayer(_goal.getPlayer());
                List<Goal> _playerGoalList = new ArrayList<>();
                _playerGoalList.add(_goal);
                _playerGoals.setGoalList(_playerGoalList);
                playGoalMap.put(_player, _playerGoals);
            } else {
                _playerGoals.addGoal(_goal);
            }
        }

        List<PlayerGoals> playerGoalsList = new ArrayList<>();
        for (PlayerGoals _playerGoals : playGoalMap.values()) {
            playerGoalsList.add(_playerGoals);
        }

        Collections.sort(playerGoalsList, new PlayerGoals.SortByGoals());


        return playerGoalsList;
    }

    public List<PlayerGoals> getResultsByLeague(Long leagueId) {
        Map<Player, PlayerGoals> playGoalMap = new HashMap<>();

        League league = leagueManagementRepository.findById(leagueId).get();
        List<Tour> tourList = league.getTours();
        for (Tour tour : tourList) {
            List<Game> gameList = tour.getGameList();
            for (Game game : gameList) {
                Long gameId = game.getId();
                List<Goal> goalList = goalRepository.findByGameId(gameId);
                List<Team> teamList = league.getTeamList();

                for (Goal _goal : goalList) {
                    if (_goal.getGame() == null || !_goal.getGame().isCompleted() || _goal.getGame().isTeamAHasTechnicalDefeat() || _goal.getGame().isTeamBHasTechnicalDefeat()) {
                        continue;
                    }
                    for (Team _team : teamList) {
                        List<Player> playerList = _team.getPlayers();
                        for (Player _player : playerList) {
                            PlayerGoals _playerGoals = playGoalMap.get(_player);
                            if (_playerGoals == null) {
                                _playerGoals = new PlayerGoals();
                                _playerGoals.setPlayer(_goal.getPlayer());
                                List<Goal> _playerGoalList = new ArrayList<>();
                                _playerGoalList.add(_goal);
                                _playerGoals.setGoalList(_playerGoalList);
                                playGoalMap.put(_player, _playerGoals);
                            } else {
                                _playerGoals.addGoal(_goal);
                            }
                        }
                    }
                }
            }
        }



        List<PlayerGoals> playerGoalsList = new ArrayList<>(playGoalMap.values());

        playerGoalsList.sort(new PlayerGoals.SortByGoals());

        return playerGoalsList;
    }


}
