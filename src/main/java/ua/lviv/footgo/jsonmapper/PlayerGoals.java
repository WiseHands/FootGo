package ua.lviv.footgo.jsonmapper;

import ua.lviv.footgo.entity.Goal;
import ua.lviv.footgo.entity.Player;

import java.util.List;

public class PlayerGoals {
    Player player;
    List<Goal> goalList;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Goal> getGoalList() {
        return goalList;
    }

    public void setGoalList(List<Goal> goalList) {
        this.goalList = goalList;
    }

    public void addGoal(Goal goal) {
        this.goalList.add(goal);
    }
}
