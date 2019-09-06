package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.Goal;
import ua.lviv.footgo.jsonmapper.PlayerGoals;
import ua.lviv.footgo.repository.GoalRepository;
import ua.lviv.footgo.service.TopScorerService;

import java.util.List;


@RestController
@RequestMapping(path = "/details")
public class TopScorerApi {
    @Autowired
    TopScorerService topScorerService;

    @GetMapping(value = "/all", consumes = "application/json", produces = "application/json")
    public List<Goal> getResults() {
        List goalList = topScorerService.getResults();
    return goalList;
    }

}