
package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.Game;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.repository.GameRepository;
import ua.lviv.footgo.repository.TeamRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/games")
public class GameApiController {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TeamRepository teamRepository;

    @GetMapping("/{id}")
    public Game getTeam(@PathVariable Long id) {
        Game game = gameRepository.findById(id).get();
        return game;

    }


    @RequestMapping(value = "/all", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public List<Game> getResults() {
        List<Game> gameList = (List<Game>) gameRepository.findAll();
        return gameList;
    }

    @RequestMapping(value = "/team", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public List<Game> getResults(@RequestParam Long teamId) {
        Team team = teamRepository.findById(teamId).get();
        List<Game> homeGames = gameRepository.findByFirstTeam(team);
        List<Game> guestGames = gameRepository.findBySecondTeam(team);
        List<Game> allGames = Stream.concat(homeGames.stream(), guestGames.stream())
                .collect(Collectors.toList());
        Collections.sort(allGames, new Game.SortByTour());
        return  allGames;
    }

}
