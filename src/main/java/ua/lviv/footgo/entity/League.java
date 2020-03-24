package ua.lviv.footgo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Team> teamList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Tour> tours;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public Long getId() {
        return id;
    }


    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public void addTeam(Team team) {
        if(this.teamList == null) {
            teamList = new ArrayList<Team>();
        }
        teamList.add(team);
    }

    public void addTour(Tour tour) {
        if(this.tours == null) {
            tours = new ArrayList<Tour>();
        }
        tours.add(tour);
    }
}
