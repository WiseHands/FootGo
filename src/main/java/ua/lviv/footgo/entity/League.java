package ua.lviv.footgo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Tour> tours;

    @Column
    private boolean submissionsOpened;

    public boolean isSubmissionsOpened() {
        return submissionsOpened;
    }

    public void setSubmissionsOpened(boolean submissionsOpened) {
        this.submissionsOpened = submissionsOpened;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public Long getId() {
        return id;
    }

    public boolean getIsSubmissionOpened() {
        return submissionsOpened;
    }

    public void setIsSubmissionOpened(boolean show) {
        this.submissionsOpened = show;
    }
}
