package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.League;
import ua.lviv.footgo.entity.Season;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.repository.LeagueManagementRepository;
import ua.lviv.footgo.repository.SeasonRepository;
import ua.lviv.footgo.repository.TeamRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/api/season")
public class LeagueApiController {

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    LeagueManagementRepository leagueRepository;

    public static class LeagueCreateRequestBody {

        private String name;
        private List<Long> teamList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Long> getTeamList() {
            return teamList;
        }

        public void setTeamList(List<Long> teamList) {
            this.teamList = teamList;
        }

        public LeagueCreateRequestBody() {

        }

    }

    @PostMapping(value = "/{seasonId}/league", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public League create(@PathVariable Long seasonId, @RequestBody LeagueCreateRequestBody body) {
        Season season = seasonRepository.findById(seasonId).get();
        League league = new League();
        league.setName(body.name);
        System.out.println(body.name);
        for(Long teamId : body.teamList) {
            Team team = teamRepository.findById(teamId).get();
            league.addTeam(team);
            System.out.println(team.getTeamName());
        }
        league = leagueRepository.save(league);
        season.addLeague(league);
        seasonRepository.save(season);
        return league;

    }
}
