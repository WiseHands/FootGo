package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.Tournament;
import ua.lviv.footgo.repository.TournamentRepository;

@RestController
@RequestMapping(path = "/tournament")
public class TournamentManagementController {

    @Autowired
    private TournamentRepository tournamentRepository;

    @PutMapping(value = "/{tournamentId}/edit", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Tournament editTournament(@PathVariable Long tournamentId, @RequestParam String tournamentName, @RequestParam String tournamentNameEn, @RequestParam String tournamentDescription, @RequestParam String tournamentDescriptionEn) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        tournament.setName(tournamentName);
        tournament.setNameEn(tournamentNameEn);
        tournament.setDescription(tournamentDescription);
        tournament.setDescriptionEn(tournamentDescriptionEn);
        tournamentRepository.save(tournament);

        return tournament;
    }
}
