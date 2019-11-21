package ua.lviv.footgo.jsonmapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ua.lviv.footgo.entity.Team;

import java.util.Comparator;

public class TeamResults {
    @JsonIgnore
    private Team team;

    private String teamName;
    private Integer points = 0;
    private Integer numberOfGames = 0;
    private Integer numberOfWins = 0;
    private Integer numberOfDraws = 0;
    private Integer numberOfLoses = 0;
    private Integer numberOfGoalsScored = 0;
    private Integer numberOfGoalsMissed = 0;

    public static class TeamResultsComparator implements Comparator<TeamResults>
    {
        public int compare(TeamResults a, TeamResults b)
        {
            if(a.points == b.points) {
                if(a.team.getAdditionalPoints() != 0 || b.getTeam().getAdditionalPoints() != 0) {
                    return b.getTeam().getAdditionalPoints() - a.getTeam().getAdditionalPoints();
                }

                int aDiff = a.numberOfGoalsScored - a.numberOfGoalsMissed;
                int bDiff = b.numberOfGoalsScored - b.numberOfGoalsMissed;
                return bDiff - aDiff;
            }
            return b.points - a.points;
        }
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
        this.teamName = team.getTeamName();
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(Integer numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public Integer getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(Integer numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public Integer getNumberOfDraws() {
        return numberOfDraws;
    }

    public void setNumberOfDraws(Integer numberOfDraws) {
        this.numberOfDraws = numberOfDraws;
    }

    public Integer getNumberOfLoses() {
        return numberOfLoses;
    }

    public void setNumberOfLoses(Integer numberOfLoses) {
        this.numberOfLoses = numberOfLoses;
    }

    public Integer getNumberOfGoalsScored() {
        return numberOfGoalsScored;
    }

    public void setNumberOfGoalsScored(Integer numberOfGoalsScored) {
        this.numberOfGoalsScored = numberOfGoalsScored;
    }

    public Integer getNumberOfGoalsMissed() {
        return numberOfGoalsMissed;
    }

    public void setNumberOfGoalsMissed(Integer numberOfGoalsMissed) {
        this.numberOfGoalsMissed = numberOfGoalsMissed;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void addWin(Integer numberOfScoredGoals, Integer numberOfGoalsMissed) {
        this.numberOfGoalsScored = this.numberOfGoalsScored + numberOfScoredGoals;
        this.numberOfGoalsMissed = this.numberOfGoalsMissed + numberOfGoalsMissed;
        this.points += 3;
        this.numberOfWins++;
        this.numberOfGames++;
    }

    public void addTechnicalWin() {
        int technicalGoals = 3;
        this.numberOfGoalsScored = this.numberOfGoalsScored + technicalGoals;
        this.points += 3;
        this.numberOfWins++;
        this.numberOfGames++;
    }

    public void addTechnicalLose() {
        int technicalGoals = 3;
        this.numberOfGoalsMissed = this.numberOfGoalsMissed + technicalGoals;
        this.numberOfLoses++;
        this.numberOfGames++;
    }

    public void addDraw(Integer numberOfScoredGoals, Integer numberOfGoalsMissed) {
        this.numberOfGoalsScored = this.numberOfGoalsScored + numberOfScoredGoals;
        this.numberOfGoalsMissed = this.numberOfGoalsMissed + numberOfGoalsMissed;
        this.points += 1;
        this.numberOfDraws++;
        this.numberOfGames++;
    }

    public void addLoss(Integer numberOfScoredGoals, Integer numberOfGoalsMissed) {
        this.numberOfGoalsScored = this.numberOfGoalsScored + numberOfScoredGoals;
        this.numberOfGoalsMissed = this.numberOfGoalsMissed + numberOfGoalsMissed;
        this.numberOfLoses++;
        this.numberOfGames++;
    }
}
