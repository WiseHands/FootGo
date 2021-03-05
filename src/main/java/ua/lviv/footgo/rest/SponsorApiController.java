package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.*;
import ua.lviv.footgo.repository.SponsorRepository;
import java.util.List;

@RestController
@RequestMapping(path = "/sponsor")
public class SponsorApiController {

    @Autowired
    SponsorRepository sponsorRepository;

    @GetMapping(value = "/all", consumes = "application/json", produces = "application/json")
    public List<Sponsor> getAll() {
        List<Sponsor> requests = (List<Sponsor>) sponsorRepository.findAll();
        return requests;
    }

    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public Sponsor getById(@PathVariable Long id) {
        Sponsor request = sponsorRepository.findById(id).get();
        return request;
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public Sponsor update(@PathVariable Long id, @RequestParam String sponsorName, @RequestParam String sponsorUrl,
                          @RequestParam String logoImageUrl, @RequestParam String logoImageUrlDark, @RequestBody(required = false) String descriptionText) {
        Sponsor sponsor = sponsorRepository.findById(id).get();
        sponsor.setSponsorName(sponsorName);
        sponsor.setSponsorUrl(sponsorUrl);

        sponsor.setLogoImageUrl(logoImageUrl);
        sponsor.setLogoImageUrlDark(logoImageUrlDark);

        sponsor.setDescriptionText(descriptionText);

        sponsorRepository.save(sponsor);
        return sponsor;
    }

    @PutMapping(value = "/{id}/setActive", consumes = "application/json", produces = "application/json")
    public Sponsor setActive(@PathVariable Long id, @RequestParam Boolean isActive) {
        Sponsor sponsor = sponsorRepository.findById(id).get();
        sponsor.setActive(isActive);

        sponsorRepository.save(sponsor);
        return sponsor;
    }

}
