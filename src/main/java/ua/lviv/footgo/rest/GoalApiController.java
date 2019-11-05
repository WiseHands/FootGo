
package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.Game;
import ua.lviv.footgo.entity.Goal;
import ua.lviv.footgo.entity.Player;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.repository.GameRepository;
import ua.lviv.footgo.repository.GoalRepository;
import ua.lviv.footgo.repository.PlayerRepository;
import ua.lviv.footgo.repository.TeamRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/api/goal")
public class GoalApiController {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    GoalRepository goalRepository;

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public void editGoal(@PathVariable Long id, @RequestParam Long playerId, @RequestParam Integer goalMinute, @RequestParam Integer goalVideoSec) {
        Goal goal = goalRepository.findById(id).get();
        if (playerId != null) {
            Player player = playerRepository.findById(playerId).get();
            goal.setPlayer(player);
        }
        if (goalMinute != null) {
            goal.setTime(goalMinute);
        }
        if (goalVideoSec != null) {
            goal.setVideoSeconds(goalVideoSec);
        }
        goalRepository.save(goal);
    }

}
