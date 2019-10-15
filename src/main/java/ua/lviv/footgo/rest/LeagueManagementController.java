package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.League;
import ua.lviv.footgo.repository.LeagueManagementRepository;

import java.util.List;


@RestController
@RequestMapping(path = "/league")
public class LeagueManagementController {
    @Autowired LeagueManagementRepository leagueManagementRepository;
    @PutMapping(value = "/submissions/{submissionsOpened}", consumes = "application/json", produces = "application/json")
    public void markOpened(@PathVariable boolean submissionsOpened) {
        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        League league = leagueList.get(0);
        league.setIsSubmissionOpened(submissionsOpened);

        leagueManagementRepository.save(league);
    }

    @GetMapping(value = "/submissions", consumes = "application/json", produces = "application/json")
    public League getValue() {
        List<League> leagueList = (List<League>) leagueManagementRepository.findAll();
        League league = leagueList.get(0);
        return league;
    }

}