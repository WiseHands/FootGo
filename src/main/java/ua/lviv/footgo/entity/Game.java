package ua.lviv.footgo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.text.ParseException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private OffsetDateTime gameTime;

    @Column
    private String videoUrl;

    @Column(columnDefinition="TEXT")
    private String descriptionText;

    @Column
    private Boolean isTeamAHasTechnicalDefeat;

    @Column
    private Boolean isTeamBHasTechnicalDefeat;

    @Column
    private String location;

    @OneToOne
    private Team firstTeam;

    @OneToOne
    private Team secondTeam;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.PERSIST)
    private GameStats teamAStats;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.PERSIST)
    private GameStats teamBStats;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Goal> teamAGoals;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Goal> teamBGoals;

    public List<Card> getTeamACards() {
        return teamACards;
    }

    public List<Card> getTeamBCards() {
        return teamBCards;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<Card> teamACards;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Card> teamBCards;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Penalty> teamAPenalty;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Penalty> teamBPenalty;

    public List<Penalty> getTeamAPenalty() {
        return teamAPenalty;
    }

    public List<Penalty> getTeamBPenalty() {
        return teamBPenalty;
    }

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Tour tour;

    private boolean isCompleted;

    public static class SortByTour implements Comparator<Game> {
        public int compare(Game a, Game b) {
            return b.tour.getTourNumber() - a.tour.getTourNumber();
        }
    }

    public boolean hasTeamAWin() {
        if(this.teamAGoals == null) {
            this.teamAGoals = new ArrayList<>();
        }
        if(this.teamBGoals == null) {
            this.teamBGoals = new ArrayList<>();
        }
        return  teamAGoals.size() > teamBGoals.size();
    }

    public boolean hasTeamBWin() {
        if(this.teamAGoals == null) {
            this.teamAGoals = new ArrayList<>();
        }
        if(this.teamBGoals == null) {
            this.teamBGoals = new ArrayList<>();
        }
        return  teamAGoals.size() < teamBGoals.size();
    }

    public void setTeamATechnicalDefeat() {
        this.isTeamAHasTechnicalDefeat = true;
        this.isTeamBHasTechnicalDefeat = false;
    }

    public void setTeamBTechnicalDefeat() {
       this.isTeamBHasTechnicalDefeat = true;
       this.isTeamAHasTechnicalDefeat = false;
    }

    public boolean isTeamAHasTechnicalDefeat() {
        if (this.isTeamAHasTechnicalDefeat == null) {
            return false;
        }
        return this.isTeamAHasTechnicalDefeat;
    }

    public boolean isTeamBHasTechnicalDefeat() {
        if (this.isTeamBHasTechnicalDefeat == null) {
            return false;
        }
        return this.isTeamBHasTechnicalDefeat;
    }

    public void clearTechnicalDefeat() {
        this.isTeamBHasTechnicalDefeat = false;
        this.isTeamAHasTechnicalDefeat = false;
    }

    @JsonIgnore
    public boolean isADraw() {
        return teamAGoals.size() == teamBGoals.size();
    }

    public OffsetDateTime getGameTime() {
        return gameTime;
    }

    public void setGameTime(OffsetDateTime gameTime) {
        this.gameTime = gameTime;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Team getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(Team firstTeam) {
        this.firstTeam = firstTeam;
    }

    public Team getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(Team secondTeam) {
        this.secondTeam = secondTeam;
    }

    public List<Goal> getTeamAGoals() {
        return teamAGoals;
    }

    public void setTeamAGoals(List<Goal> teamAGoals) {
        this.teamAGoals = teamAGoals;
    }

    public List<Goal> getTeamBGoals() {
        return teamBGoals;
    }

    public void setTeamBGoals(List<Goal> teamBGoals) {
        this.teamBGoals = teamBGoals;
    }

    public Long getId() {
        return id;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public GameStats getTeamAStats() {
        return teamAStats;
    }

    public void setTeamAStats(GameStats teamAStats) {
        this.teamAStats = teamAStats;
    }

    public GameStats getTeamBStats() {
        return teamBStats;
    }

    public void setTeamBStats(GameStats teamBStats) {
        this.teamBStats = teamBStats;
    }

    public void addGoalForFirstTeam(Goal goal) {
        if(this.teamAGoals == null) {
            this.teamAGoals = new ArrayList<>();
        }
        this.teamAGoals.add(goal);
    }

    public void removeGoalForFirstTeam(Goal goal) {
        this.teamAGoals.remove(goal);
    }

    public void addGoalForSecondTeam(Goal goal) {
        if(this.teamBGoals == null) {
            this.teamBGoals = new ArrayList<>();
        }
        this.teamBGoals.add(goal);
    }

    public void removeGoalForSecondTeam(Goal goal) {
        this.teamBGoals.remove(goal);
    }

    public void addCardForFirstTeamPlayer(Card card) {
        if(this.teamACards == null) {
            this.teamACards = new ArrayList<>();
        }
        this.teamACards.add(card);
    }

    public void addCardForSecondTeamPlayer(Card card) {
        if(this.teamBCards == null) {
            this.teamBCards = new ArrayList<>();
        }
        this.teamBCards.add(card);
    }

    public void removeCardForFirstTeam(Card card) {
        this.teamACards.remove(card);
    }

    public void removeCardForSecondTeam(Card card) {
        this.teamBCards.remove(card);
    }

    public void addPenaltyForFirstTeamPlayer(Penalty penalty) {
        this.teamAPenalty.add(penalty);
    }

    public void addPenaltyForSecondTeamPlayer(Penalty penalty) {
        this.teamBPenalty.add(penalty);
    }

    public void removePenaltyForFirstTeam(Penalty penalty) {
        this.teamAPenalty.remove(penalty);
    }

    public void removePenaltyForSecondTeam(Penalty penalty) {
        this.teamBPenalty.remove(penalty);
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public String formatTime() throws ParseException {
        ZoneId zone = ZoneId.of("Europe/Kiev");

        OffsetDateTime time = this.gameTime;
        if(time == null) {
            return "";
        }

        ZonedDateTime zonedDateTime = time.atZoneSameInstant(zone);

/*        // set UTC zone in date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        dateFormat.parse(time);
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        dateFormat.setTimeZone(timeZone);
        Date hourTime = dateFormat.parse(time);

        // get time
        String[] hours = String.valueOf(hourTime).split(" ");
        String[] hoursTime = hours[3].split(":");
        // get and reformat date

        String newDate = hoursTime[0] + ":" + hoursTime[1];*/

        //return time.getHour() + ":" + time.getMinute();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm");
        String minute = zonedDateTime.format(formatter);
        return zonedDateTime.getHour() + ":" + minute;
    }

    public String formatDate() throws ParseException {
        ZoneId zone = ZoneId.of("Europe/Kiev");

        OffsetDateTime time = this.gameTime;
        if(time == null) {
            return "TBD";
        }

        ZonedDateTime zonedDateTime = time.atZoneSameInstant(zone);

/*        // set UTC zone in date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        dateFormat.parse(time);
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        dateFormat.setTimeZone(timeZone);
        // get and reformat date
        String[] dates = time.split("T");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dates[0]);
        SimpleDateFormat format1 = new SimpleDateFormat("EEEE, MMM d");
        String[] formatDate = format1.format(date).split(", ");
        String month = formatDate[1].substring(0, 1).toUpperCase() + formatDate[1].substring(1);
        String newDate = month;*/

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM");
        String month = zonedDateTime.format(formatter);

        return month + " " + zonedDateTime.getDayOfMonth();
    }

    public String formatGoalsForTeamA() {
        if (!isCompleted) {
            return "";
        } else {
            return String.valueOf(teamAGoals.size());
        }
    }

    public String formatGoalsForTeamB() {
        if (!isCompleted) {
            return "";
        } else {
            return String.valueOf(teamBGoals.size());
        }
    }

    public boolean isNoTechnicalDefeat() {
        return !this.isTeamAHasTechnicalDefeat() && !this.isTeamBHasTechnicalDefeat();
    }
}
