package ua.lviv.footgo.jsonmapper;

import java.util.List;

public class TeamCreationRequestJsonBody {

    private String teamName;


    private FootballCaptain captain;

    private List<FootballPlayer> playerList;

    public FootballCaptain getCaptain() {
        return captain;
    }

    public void setCaptain(FootballCaptain captain) {
        this.captain = captain;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<FootballPlayer> getPlayerList() {

        return playerList;
    }

    public void setPlayerList(List<FootballPlayer> playerList) {
        this.playerList = playerList;
    }


}
