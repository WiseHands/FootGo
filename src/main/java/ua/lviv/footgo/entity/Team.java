package ua.lviv.footgo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/* Map this entity class to user_account table. */
@Entity(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String teamName;

    @Column
    private String logoImageUrl;

    @Column
    private String logoImageUrlDark;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Player> players;

    @OneToOne
    @JoinColumn
    private Captain captain;

    private int additionalPoints;

    public int getAdditionalPoints() {
        return additionalPoints;
    }

    public void setAdditionalPoints(int additionalPoints) {
        this.additionalPoints = additionalPoints;
    }

    public String getLogoImageUrl() {
        return logoImageUrl;
    }

    public void setLogoImageUrl(String logoImageUrl) {
        this.logoImageUrl = logoImageUrl;
    }

    public String getLogoImageUrlDark() {
        return logoImageUrlDark;
    }

    public void setLogoImageUrlDark(String logoImageUrlDark) {
        this.logoImageUrlDark = logoImageUrlDark;
    }

    public Captain getCaptain() {
        return captain;
    }

    public void setCaptain(Captain captain) {
        this.captain = captain;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Long getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public void addPlayer(Player player) {
        if(this.players == null) {
            this.players = new ArrayList<>();
        }
        this.players.add(player);
    }


}