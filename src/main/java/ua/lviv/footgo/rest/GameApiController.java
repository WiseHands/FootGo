
package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.Game;
import ua.lviv.footgo.entity.Goal;
import ua.lviv.footgo.entity.Player;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.repository.GameRepository;
import ua.lviv.footgo.repository.GoalRepository;
import ua.lviv.footgo.repository.TeamRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/api/game")
public class GameApiController {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    GoalRepository goalRepository;

    @GetMapping("/{id}")
    public Game getTeam(@PathVariable Long id) {
        Game game = gameRepository.findById(id).get();
        return game;

    }


    @GetMapping(value = "/all", consumes = "application/json", produces = "application/json")
    public List<Game> getResults() {
        List<Game> gameList = (List<Game>) gameRepository.findAll();
        return gameList;
    }

    @GetMapping(value = "/team", consumes = "application/json", produces = "application/json")
    public List<Game> getResults(@RequestParam Long teamId) {
        Team team = teamRepository.findById(teamId).get();
        List<Game> homeGames = gameRepository.findByFirstTeam(team);
        List<Game> guestGames = gameRepository.findBySecondTeam(team);
        List<Game> allGames = Stream.concat(homeGames.stream(), guestGames.stream())
                .collect(Collectors.toList());
        Collections.sort(allGames, new Game.SortByTour());
        return  allGames;
    }

    @PostMapping(value = "/{id}/goal", consumes = "application/json", produces = "application/json")
    public Goal addGoal(@PathVariable Long id, @RequestParam Player playerId, @RequestParam int goalMinute, @RequestParam  boolean homeTeamGoal) {
        Game game = gameRepository.findById(id).get();
        Goal goal = new Goal();
        goal.setPlayer(playerId);
        goal.setTime(goalMinute);
        goal.setGame(game);
        if(homeTeamGoal) {
            game.addGoalForFirstTeam(goal);
        } else {
            game.addGoalForSecondTeam(goal);
        }
        goalRepository.save(goal);
        gameRepository.save(game);
        return goal;
    }

    @DeleteMapping(value = "/{gameId}/goal/{goalId}", consumes = "application/json", produces = "application/json")
    public void deleteGoal(@PathVariable Long goalId, @PathVariable Long gameId, @RequestParam Boolean isHomeTeamGoal) {
        Game game = gameRepository.findById(gameId).get();
        Goal goal = goalRepository.findById(goalId).get();
        if(isHomeTeamGoal) {
            game.removeGoalForFirstTeam(goal);
        } else {
            game.removeGoalForSecondTeam(goal);
        }
        gameRepository.save(game);
    }
}
