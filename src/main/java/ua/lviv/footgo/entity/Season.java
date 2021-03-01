package ua.lviv.footgo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "season")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_en")
    private String name_en;

    @Column
    private boolean submissionsOpened;

    @Column
    private Boolean showSponsors;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TeamSignUpRequest> teamSubmissionList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Team> teamList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Sponsor> sponsorList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<League> leagueList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Cup> cupList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TeamSignUpRequest> getTeamSubmissionList() {
        return teamSubmissionList;
    }

    public void setTeamSubmissionList(List<TeamSignUpRequest> teamSubmissionList) {
        this.teamSubmissionList = teamSubmissionList;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public List<Sponsor> getSponsorList() {
        return sponsorList;
    }

    public void setSponsorList(List<Sponsor> sponsorList) {
        this.sponsorList = sponsorList;
    }

    public List<League> getLeagueList() {
        return leagueList;
    }

    public void setLeagueList(List<League> leagueList) {
        this.leagueList = leagueList;
    }

    public List<Cup> getCupList() {
        return cupList;
    }

    public void setCupList(List<Cup> cupList) {
        this.cupList = cupList;
    }

    public boolean getIsSubmissionOpened() {
        return submissionsOpened;
    }

    public void setIsSubmissionOpened(boolean show) {
        this.submissionsOpened = show;
    }

    public Boolean getShowSponsors() {
        return showSponsors;
    }

    public void setShowSponsors(Boolean showSponsors) {
        this.showSponsors = showSponsors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNameEn(String name_en) {
        this.name_en = name_en;
    }

    public String getNameEn() {
        return name_en;
    }


    public void addTeam(Team team) {
        if(this.teamList == null) {
            this.teamList = new ArrayList<>();
        }
        this.teamList.add(team);
    }
    public void addSponsor(Sponsor sponsor) {
        if(this.sponsorList == null) {
            this.sponsorList = new ArrayList<>();
        }
        this.sponsorList.add(sponsor);
    }
    public void removeSponsor(Sponsor sponsor) {
        if(this.sponsorList == null) {
            return;
        }
        sponsorList.remove(sponsor);
    }
    public void addLeague(League league) {
        if(this.leagueList == null) {
            this.leagueList = new ArrayList<>();
        }
        this.leagueList.add(league);
    }
    public void addCup(Cup cup) {
        if(this.cupList == null) {
            this.cupList = new ArrayList<>();
        }
        this.cupList.add(cup);
    }
}
