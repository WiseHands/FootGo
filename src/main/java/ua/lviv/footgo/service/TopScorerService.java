package ua.lviv.footgo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.footgo.entity.Game;
import ua.lviv.footgo.entity.Goal;
import ua.lviv.footgo.entity.Player;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.jsonmapper.PlayerGoals;
import ua.lviv.footgo.jsonmapper.TeamResults;
import ua.lviv.footgo.repository.GameRepository;
import ua.lviv.footgo.repository.GoalRepository;
import ua.lviv.footgo.repository.TeamRepository;

import java.util.*;

@Service
public class TopScorerService {

    @Autowired
    GoalRepository goalRepository;



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


}
