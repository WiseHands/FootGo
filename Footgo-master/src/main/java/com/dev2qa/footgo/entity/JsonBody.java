package com.dev2qa.footgo.entity;

import java.util.List;

public class JsonBody {

    private String teamName;
    private String captainName;
    private String captainPhone;
    private String captainEmail;
    private List<Gamer> playerList;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    public String getCaptainPhone() {
        return captainPhone;
    }

    public void setCaptainPhone(String captainPhone) {
        this.captainPhone = captainPhone;
    }

    public String getCaptainEmail() {
        return captainEmail;
    }

    public void setCaptainEmail(String captainEmail) {
        this.captainEmail = captainEmail;
    }

    public List<Gamer> getPlayerList() {

        return playerList;
    }

    public void setPlayerList(List<Gamer> playerList) {
        this.playerList = playerList;
    }


}
