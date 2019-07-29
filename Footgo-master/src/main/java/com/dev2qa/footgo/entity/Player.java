package com.dev2qa.footgo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @javax.persistence.Column(name = "first_name")
    private String firstName;

    @javax.persistence.Column(name = "last_name")
    private String lastName;

    @javax.persistence.Column(name = "phone")
    private String phone;

    @javax.persistence.Column(name = "number")
    private Integer number;

//    @javax.persistence.Column(name = "isCaptain")
//    private Boolean isCaptain;

    @javax.persistence.Column(name = "email")
    private String email;

//    public Boolean getCaptain() {
//        return isCaptain;
//    }
//
//    public void setCaptain(Boolean captain) {
//        isCaptain = captain;
//    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
