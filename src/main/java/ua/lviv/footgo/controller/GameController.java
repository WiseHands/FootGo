
package ua.lviv.footgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.footgo.entity.Game;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.jsonmapper.TeamResults;
import ua.lviv.footgo.repository.GameRepository;
import ua.lviv.footgo.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/games")
public class GameController {

    @Autowired
    GameRepository gameRepository;


    @RequestMapping(value = "/all", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public List<Game> getResults() {
        List<Game> gameList = (List<Game>) gameRepository.findAll();
        return gameList;
    }

}
