package ua.lviv.footgo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "goal")
public class Goal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private Integer time;

    @Column
    private Integer videoSeconds;

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


    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getVideoSeconds() {
        return videoSeconds;
    }

    public void setVideoSeconds(Integer videoSeconds) {
        this.videoSeconds = videoSeconds;
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
