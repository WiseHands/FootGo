package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.Game;
import ua.lviv.footgo.entity.Season;
import ua.lviv.footgo.entity.Tournament;
import ua.lviv.footgo.repository.SeasonRepository;
import ua.lviv.footgo.repository.TournamentRepository;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/api/tournament")
public class SeasonApiController {

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @PostMapping(value = "/{tournamentId}/seasons/new", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Season createSeason(@PathVariable Long tournamentId, @RequestParam String seasonName, @RequestParam String seasonNameEn) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        Season season = new Season();
        season.setName(seasonName);
        season.setNameEn(seasonNameEn);
        tournament.addSeason(season);
        tournamentRepository.save(tournament);

        return season;
    }
}
