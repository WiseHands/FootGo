package ua.lviv.footgo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Team> teamList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Tour> tours;



    public List<Tour> getTours() {
        return tours;
    }

    public Long getId() {
        return id;
    }

}
