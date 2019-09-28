package ua.lviv.footgo.jsonmapper;

import ua.lviv.footgo.entity.Goal;
import ua.lviv.footgo.entity.Player;

import java.util.Comparator;
import java.util.List;

public class PlayerGoals {
    Player player;
    List<Goal> goalList;

    public static class SortByGoals implements Comparator<PlayerGoals>
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(PlayerGoals a, PlayerGoals b)
        {
            return b.goalList.size() - a.goalList.size();
        }
    }

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

    public String formatPosition(Integer index){
        return "" + (1 +index);
    }
}
