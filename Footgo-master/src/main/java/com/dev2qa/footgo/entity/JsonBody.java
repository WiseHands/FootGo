package com.dev2qa.footgo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class JsonBody {

    private String teamName;
    private String captainName;
    private String captainPhone;
    private String captainEmail;
    private List<Player> playerList;



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

    public List<Player> getPlayerList() {

        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }


}
