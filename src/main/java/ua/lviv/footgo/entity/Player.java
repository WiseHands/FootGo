package ua.lviv.footgo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String imageUrl;
    private Integer number;

    private Boolean isActive;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Team team;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String formatNumber() {
        if(this.number == null) {
            return "";
        }
        return  "" + this.number;
    }

    public boolean isTwoDigits() {
        if(this.number == null) return false;
        return this.number > 9;
    }

    public String getPlayerFirstName() {
        if(this.firstName != null) {
            return  this.firstName;
        }
        return "";
    }

    public String getPlayerLastName() {
        if(this.lastName != null) {
            return  this.lastName;
        }
        return "";
    }

}
