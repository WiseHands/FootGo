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

    @PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Season createSeason(@RequestParam String seasonName) {
        Season season = new Season();
        season.setName(seasonName);
        seasonRepository.save(season);

        return season;
    }
}
