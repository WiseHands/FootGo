package ua.lviv.footgo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.footgo.entity.*;
import ua.lviv.footgo.jsonmapper.PlayerGoals;
import ua.lviv.footgo.jsonmapper.TeamResults;
import ua.lviv.footgo.repository.*;

import java.util.*;

@Service
public class TopScorerService {

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    LeagueManagementRepository leagueManagementRepository;

    @Autowired
    CupManagementRepository cupManagementRepository;

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
        List<Goal> goalList = (List<Goal>) goalRepository.findAll();
        League league = leagueManagementRepository.findById(leagueId).get();
        List<Team> teamList = league.getTeamList();

        for(Goal _goal : goalList) {
            if(_goal.getGame() == null || !_goal.getGame().isCompleted() || _goal.getGame().isTeamAHasTechnicalDefeat() || _goal.getGame().isTeamBHasTechnicalDefeat()) {
                continue;
            }
            Player _player = _goal.getPlayer();
            final PlayerGoals[] _playerGoals = {playGoalMap.get(_player)};
            teamList.forEach(team -> {
                if (_player.getTeam().getId().equals(team.getId())) {
                    if(_playerGoals[0] == null) {
                        _playerGoals[0] = new PlayerGoals();
                        _playerGoals[0].setPlayer(_goal.getPlayer());
                        List<Goal> _playerGoalList = new ArrayList<>();
                        _playerGoalList.add(_goal);
                        _playerGoals[0].setGoalList(_playerGoalList);
                        playGoalMap.put(_player, _playerGoals[0]);
                    } else {
                        _playerGoals[0].addGoal(_goal);
                    }
                }
            });
        }

        List<PlayerGoals> playerGoalsList = new ArrayList<>(playGoalMap.values());

        playerGoalsList.sort(new PlayerGoals.SortByGoals());

        return playerGoalsList;
    }

    public List<PlayerGoals> getResultsByCup(Long cupId) {
        Map<Player, PlayerGoals> playGoalMap = new HashMap<>();
        List<Goal> goalList = (List<Goal>) goalRepository.findAll();
        Cup cup = cupManagementRepository.findById(cupId).get();

        List<Team> teamList = cup.getTeamList();
        List<Tour> tourList = cup.getTours();

        tourList.forEach(tour -> {
            List<Game> gameList = tour.getGameList();
            gameList.forEach(game -> {
                //System.out.println("GAME ID " + game.getId());
                List<Goal> _goalList = goalRepository.findByGameId(game.getId());
                _goalList.forEach(_goal -> {
                    System.out.println("GOAL GAME ID " + _goal.getGame().getId());
                });
            });
        });

        for(Goal _goal : goalList) {
            if(_goal.getGame() == null || !_goal.getGame().isCompleted() || _goal.getGame().isTeamAHasTechnicalDefeat() || _goal.getGame().isTeamBHasTechnicalDefeat()) {
                continue;
            }
            Player _player = _goal.getPlayer();
            final PlayerGoals[] _playerGoals = {playGoalMap.get(_player)};
            teamList.forEach(team -> {

                if (_player.getTeam().getId().equals(team.getId())) {
                    if(_playerGoals[0] == null) {
                        _playerGoals[0] = new PlayerGoals();
                        _playerGoals[0].setPlayer(_goal.getPlayer());
                        List<Goal> _playerGoalList = new ArrayList<>();
                        _playerGoalList.add(_goal);
                        _playerGoals[0].setGoalList(_playerGoalList);
                        playGoalMap.put(_player, _playerGoals[0]);
                    } else {
                        _playerGoals[0].addGoal(_goal);
                    }
                }
            });
        }

        List<PlayerGoals> playerGoalsList = new ArrayList<>(playGoalMap.values());

        playerGoalsList.sort(new PlayerGoals.SortByGoals());

        return playerGoalsList;
    }

}
