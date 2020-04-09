package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.Season;
import ua.lviv.footgo.repository.SeasonRepository;


@RestController
@RequestMapping(path = "/season")
public class SeasonManagementController {
    @Autowired SeasonRepository seasonRepository;

    @PutMapping(value = "/{seasonId}/submissions/{submissionsOpened}", consumes = "application/json", produces = "application/json")
    public void markOpened(@PathVariable Long seasonId, @PathVariable boolean submissionsOpened) {
        Season season = seasonRepository.findById(seasonId).get();
        season.setIsSubmissionOpened(submissionsOpened);
        seasonRepository.save(season);
    }

    @GetMapping(value = "/{seasonId}/submissions", consumes = "application/json", produces = "application/json")
    public Season getValue(@PathVariable Long seasonId) {
        return seasonRepository.findById(seasonId).get();
    }

}