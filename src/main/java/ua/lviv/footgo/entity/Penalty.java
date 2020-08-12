package ua.lviv.footgo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "penalty")
public class Penalty {

    public boolean penaltyGoal;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Game game;

    @ManyToOne
    @JoinColumn
    private Player player;


    public Long getId() {
        return id;
    }

    public boolean getPenaltyGoal() {
        return penaltyGoal;
    }

    public void setPenaltyGoal(boolean value) {
        this.penaltyGoal = value;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
