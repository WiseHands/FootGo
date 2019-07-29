package com.dev2qa.footgo.entity;

import javax.persistence.*;

/* Map this entity class to user_account table. */
@Entity(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @javax.persistence.Column(name = "team_name")
    private String teamName;
    @javax.persistence.Column(name = "captain_name")
    private String captainName;
    @javax.persistence.Column(name = "captain_phone")
    private String captainPhone;
    @javax.persistence.Column(name = "captain_email")
    private String captainEmail;

    // generate players list
    @javax.persistence.Column(name = "player_list")
    private String playerList;

    public String getPlayerList() {
        return playerList;
    }

    public void setPlayerList(String playerList) {
        this.playerList = playerList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

//    public List<Player> getPlayerList() {
//        return playerList;
//    }
//
//    public void setPlayerList(List<Player> playerList) {
//        this.playerList = playerList;
//    }

}