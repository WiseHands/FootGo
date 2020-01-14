package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.Captain;
import ua.lviv.footgo.entity.Player;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.entity.TeamSignUpRequest;
import ua.lviv.footgo.repository.CaptainRepository;
import ua.lviv.footgo.repository.TeamRepository;
import ua.lviv.footgo.repository.TeamSignUpRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/team")
public class TeamApiController {

    @Autowired
    TeamRepository teamRepository;
    CaptainRepository captainRepository;

    @GetMapping(value = "/all", consumes = "application/json", produces = "application/json")
    public List<Team> getAll() {
        List<Team> requests = (List<Team>) teamRepository.findAll();
        return requests;
    }

    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public Team getById(@PathVariable Long id) {
        Team request = teamRepository.findById(id).get();
        return request;
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public Team update(@PathVariable Long id, @RequestParam String name, @RequestParam String captainName, @RequestParam String captainPhone, @RequestParam String captainEmail, @RequestParam Integer additionalPoints, @RequestParam String logoImageUrl, @RequestParam String logoImageUrlDark) {
        Team team = teamRepository.findById(id).get();
        team.setTeamName(name);
        team.getCaptain().setCaptainName(captainName);
        team.getCaptain().setCaptainEmail(captainEmail);
        team.getCaptain().setCaptainPhone(captainPhone);
        team.setAdditionalPoints(additionalPoints);
        team.setLogoImageUrl(logoImageUrl);
        team.setLogoImageUrlDark(logoImageUrlDark);

        teamRepository.save(team);
        return team;
    }

    @PostMapping(value = "/{teamId}/player/new", consumes = "application/json", produces = "application/json")
    public Team create(@PathVariable Long teamId, @RequestParam String firstName, String lastName, int number, String phone, String email, String imageUrl) {
        Team team = teamRepository.findById(teamId).get();
        Player player = new Player();
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setNumber(number);
        player.setPhone(phone);
        player.setEmail(email);
        player.setImageUrl(imageUrl);
        team.addPlayer(player);
        player.setTeam(team);

        teamRepository.save(team);
        return team;
    }
}
