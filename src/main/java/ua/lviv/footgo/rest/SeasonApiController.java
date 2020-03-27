package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.Season;
import ua.lviv.footgo.repository.SeasonRepository;

@RestController
@RequestMapping(path = "/api/seasons")
public class SeasonApiController {

    @Autowired
    private SeasonRepository seasonRepository;

/*    private Faker faker = new Faker();

    private Team createTeam() {
        Team team = new Team();
        team.setTeamName(faker.name().lastName());

        return team;
    }
    private  Captain createCaptain() {
        Captain captain = new Captain();
        captain.setCaptainName(faker.name().name());
        captain.setCaptainPhone(faker.phoneNumber().phoneNumber());
        captain.setCaptainEmail(captain.getCaptainName() + "@gmail.com");

        return  captain;
    }
    private  Player createPlayer() {
        Player player = new Player();
        player.setFirstName(faker.name().firstName());
        player.setLastName(faker.name().lastName());

        return player;
    }*/
    @PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Season createSeason(@RequestParam String seasonName) {
        Season season = new Season();
        season.setName(seasonName);

/*        for (int i = 0; i < 16; i++) {
            Team team = createTeam();
            Captain captain = createCaptain();
            team.setCaptain(captain);
            for (int p = 0; p < 11; p++) {
                Player player = createPlayer();
                team.addPlayer(player);
            }
            season.addTeam(team);
        }*/
        seasonRepository.save(season);

        return season;
    }
}
