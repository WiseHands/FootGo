package ua.lviv.footgo.entity;

        import javax.persistence.*;
        import java.util.ArrayList;
        import java.util.List;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Lob
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Season> seasonList;

    @OneToOne
    private Season activeSeason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Season> getSeasonList() {
        return seasonList;
    }

    public void setSeasonList(List<Season> seasonList) {
        this.seasonList = seasonList;
    }

    public Season getActiveSeason() {
        return activeSeason;
    }

    public void setActiveSeason(Season activeSeason) {
        this.activeSeason = activeSeason;
    }

    public void addSeason(Season season) {
        if(this.seasonList == null) {
            this.seasonList = new ArrayList<>();
        }
        this.seasonList.add(season);
    }
}
