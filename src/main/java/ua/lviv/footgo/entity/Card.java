package ua.lviv.footgo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "card")
public class Card {

    public enum CardType {
        YELLOW,
        RED,
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private Integer time;

    @Enumerated
    private CardType cardType;

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

    public CardType getCard() {
        return cardType;
    }

    public void setCard(CardType card) {
        this.cardType = card;
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

    public boolean isRed() {
        return this.cardType.equals(CardType.RED);
    }
    public boolean isYellow() {
        return this.cardType.equals(CardType.YELLOW);
    }

}
