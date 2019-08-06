package ua.lviv.footgo.jsonmapper;

public class FootballPlayer {

    private String footballPlayerName;

    public FootballPlayer(){

    }

    public FootballPlayer(String footballPlayerName) {
        this.footballPlayerName = footballPlayerName;
    }

    public String getFootballPlayerName() {
        return footballPlayerName;
    }

    public void setFootballPlayerName(String footballPlayerName) {
        this.footballPlayerName = footballPlayerName;
    }
}
