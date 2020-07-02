package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.Captain;
import ua.lviv.footgo.entity.League;
import ua.lviv.footgo.entity.Season;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.repository.CaptainRepository;
import ua.lviv.footgo.repository.LeagueManagementRepository;
import ua.lviv.footgo.repository.SeasonRepository;
import ua.lviv.footgo.repository.TeamRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/season")
public class TeamCreateNewTeamLeagueApiController {

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    CaptainRepository captainRepository;

    @Autowired
    LeagueManagementRepository leagueManagementRepository;

    @PostMapping(value = "/{seasonId}/leaguelist/{leagueId}/team/new", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Team addNewTeam(@PathVariable Long seasonId, @PathVariable Long leagueId, @RequestParam String teamName, @RequestParam String captainName) {
        Season season = seasonRepository.findById(seasonId).get();
        Team team = new Team();
        team.setTeamName(teamName);
        Captain captain = new Captain();
        captain.setCaptainName(captainName);
        team.setCaptain(captain);
        season.addTeam(team);
        League league = leagueManagementRepository.findById(leagueId).get();
        league.addTeam(team);

        captainRepository.save(captain);
        teamRepository.save(team);
        leagueManagementRepository.save(league);
        return team;
    }

    @DeleteMapping(value = "/{seasonId}/leaguelist/{leagueId}/team", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public void deleteTeamFromLeague(@PathVariable Long seasonId, @PathVariable Long leagueId, @RequestParam Long teamId) {
        League league = leagueManagementRepository.findById(leagueId).get();
        Team team = teamRepository.findById(teamId).get();
        league.removeTeam(team);

        leagueManagementRepository.save(league);
    }

}
