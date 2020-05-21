package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.League;
import ua.lviv.footgo.repository.LeagueManagementRepository;

@RestController
@RequestMapping(path = "/leaguelist")
public class LeagueManagementController {

    @Autowired
    private LeagueManagementRepository leagueManagementRepository;

    @PutMapping(value = "/{leagueId}/edit", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public League editLeague(@PathVariable Long leagueId, @RequestParam String leagueName, @RequestParam String leagueNameEn) {
        League league = leagueManagementRepository.findById(leagueId).get();
        league.setName(leagueName);
        league.setNameEn(leagueNameEn);
        leagueManagementRepository.save(league);

        return league;
    }
}
