package ua.lviv.footgo.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "season")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column
    private boolean submissionsOpened;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TeamSignUpRequest> teamSubmissionList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Team> teamList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<League> leagueList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Cup> cupList;



    public boolean getIsSubmissionOpened() {
        return submissionsOpened;
    }

    public void setIsSubmissionOpened(boolean show) {
        this.submissionsOpened = show;
    }

}
