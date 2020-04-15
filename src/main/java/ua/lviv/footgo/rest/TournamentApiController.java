package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.Season;
import ua.lviv.footgo.entity.Tournament;
import ua.lviv.footgo.repository.TournamentRepository;

@RestController
@RequestMapping(path = "/api/tournament")
public class TournamentApiController {

    @Autowired
    private TournamentRepository tournamentRepository;

    @PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Tournament createTournament(@RequestParam String tournamentName, @RequestParam String tournamentDescription) {
        Tournament tournament = new Tournament();
        tournament.setName(tournamentName);
        tournament.setDescription(tournamentDescription);
        tournamentRepository.save(tournament);

        return tournament;
    }

    @PutMapping(value = "/set_active_season", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Tournament setActiveSeason(@RequestParam Long tournamentId, @RequestParam Season activeSeasonId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        tournament.setActiveSeason(activeSeasonId);

        tournamentRepository.save(tournament);

        return tournament;
    }

    @GetMapping(value = "/get_active_season", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Season getActiveSeason(@RequestParam Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();

        return tournament.getActiveSeason();
    }

}
