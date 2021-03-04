package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.Season;
import ua.lviv.footgo.entity.Sponsor;
import ua.lviv.footgo.repository.SeasonRepository;
import ua.lviv.footgo.repository.SponsorRepository;

@RestController
@RequestMapping(path = "/api/season")
public class SponsorCreateApiController {

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    SponsorRepository sponsorRepository;

    @PostMapping(value = "/{seasonId}/sponsors/new", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Sponsor createNewSponsor(@PathVariable Long seasonId, @RequestParam String sponsorName, @RequestParam String sponsorUrl,
                                 @RequestParam String logoImageUrl, @RequestParam String logoImageUrlDark, @RequestBody String descriptionText) {
        Season season = seasonRepository.findById(seasonId).get();
        Sponsor sponsor = new Sponsor();
        sponsor.setSponsorName(sponsorName);
        sponsor.setSponsorUrl(sponsorUrl);

        sponsor.setLogoImageUrl(logoImageUrl);
        sponsor.setLogoImageUrlDark(logoImageUrlDark);

        sponsor.setDescriptionText(descriptionText);

        sponsor.setActive(true);

        season.addSponsor(sponsor);

        sponsorRepository.save(sponsor);
        return sponsor;
    }

    @DeleteMapping(value = "/{seasonId}/sponsors", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void deleteSponsorFromSeason(@PathVariable Long seasonId, @RequestParam Long sponsorId) {
        Sponsor sponsor = new Sponsor();
        boolean sponsorIsPresent = sponsorRepository.findById(sponsorId).isPresent();
        if (sponsorIsPresent) {
            sponsor = sponsorRepository.findById(sponsorId).get();
        }
        Season season = seasonRepository.findById(seasonId).get();
        season.removeSponsor(sponsor);
/*        for (Sponsor item : season.getSponsorList()) {*/
            sponsorRepository.delete(sponsor);
/*        }*/

        seasonRepository.save(season);
    }

}
