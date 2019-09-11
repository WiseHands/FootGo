package ua.lviv.footgo.jsonmapper;

public class FootballPlayer {

    private String footballPlayerFirstName;
    private String footballPlayerLastName;

    public FootballPlayer(){

    }

    public FootballPlayer(String footballPlayerFirstName, String footballPlayerLastName) {
        this.footballPlayerFirstName = footballPlayerFirstName;
        this.footballPlayerLastName = footballPlayerLastName;
    }

    public String getFootballPlayerFirstName() {
        return footballPlayerFirstName;
    }

    public void setFootballPlayerFirstName(String footballPlayerFirstName) {
        this.footballPlayerFirstName = footballPlayerFirstName;
    }

    public String getFootballPlayerLastName() {
        return footballPlayerLastName;
    }

    public void setFootballPlayerLastName(String footballPlayerLastName) {
        this.footballPlayerLastName = footballPlayerLastName;
    }

}
