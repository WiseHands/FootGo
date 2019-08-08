package ua.lviv.footgo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tourNumber")
    private Integer tourNumber;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL)
    private List<Game> gameList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTourNumber() {
        return tourNumber;
    }

    public void setTourNumber(Integer tourNumber) {
        this.tourNumber = tourNumber;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public void addGame(Game game) {
        if(this.gameList == null) {
            this.gameList = new ArrayList<>();
        }
        this.gameList.add(game);
    }

    public void removeGame(Game game) {
        this.gameList.remove(game);
    }
}
