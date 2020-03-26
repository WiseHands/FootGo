package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.Captain;
import ua.lviv.footgo.entity.Season;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.repository.CaptainRepository;
import ua.lviv.footgo.repository.SeasonRepository;
import ua.lviv.footgo.repository.TeamRepository;

@RestController
@RequestMapping(path = "/api/season")
public class TeamCreateApiController {

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    CaptainRepository captainRepository;

    @PostMapping(value = "/{seasonId}/team/new", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Team createNewTeam(@PathVariable Long seasonId, @RequestParam String teamName, @RequestParam String captainName) {
        Season season = seasonRepository.findById(seasonId).get();
        Team team = new Team();
        team.setTeamName(teamName);
        Captain captain = new Captain();
        captain.setCaptainName(captainName);
        team.setCaptain(captain);
        season.addTeam(team);
        captainRepository.save(captain);
        teamRepository.save(team);
        return team;
    }

}
