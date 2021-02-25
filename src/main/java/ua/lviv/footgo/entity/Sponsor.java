package ua.lviv.footgo.entity;

import ua.lviv.footgo.util.Transliterator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/* Map this entity class to user_account table. */
@Entity(name = "sponsor")
public class Sponsor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String sponsorName;

    @Column(name = "url")
    private String sponsorUrl;

    @Column
    private String logoImageUrl;

    @Column
    private String logoImageUrlDark;

    @Column
    private Boolean isActive;

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getLogoImageUrl() {
        return logoImageUrl;
    }

    public void setLogoImageUrl(String logoImageUrl) {
        this.logoImageUrl = logoImageUrl;
    }

    public String getLogoImageUrlDark() {
        return logoImageUrlDark;
    }

    public void setLogoImageUrlDark(String logoImageUrlDark) {
        this.logoImageUrlDark = logoImageUrlDark;
    }

    public Long getId() {
        return id;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getSponsorUrl() {
        return sponsorUrl;
    }

    public void setSponsorUrl(String sponsorUrl) {
        this.sponsorUrl = sponsorUrl;
    }

    public String getSponsorNameTransliterated() {
        return Transliterator.transliterate(this.sponsorName);
    }

}