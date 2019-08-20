
package ua.lviv.footgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.lviv.footgo.entity.Captain;
import ua.lviv.footgo.entity.Game;
import ua.lviv.footgo.entity.Player;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.jsonmapper.FootballPlayer;
import ua.lviv.footgo.jsonmapper.TeamCreationRequestJsonBody;
import ua.lviv.footgo.repository.CaptainRepository;
import ua.lviv.footgo.repository.GameRepository;
import ua.lviv.footgo.repository.PlayerRepository;
import ua.lviv.footgo.repository.TeamRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(path = "/games")
public class ResultController {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TeamRepository teamRepository;




    @RequestMapping(value = "/table", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public ResponseEntity< String > buildResultsTable(@RequestBody TeamCreationRequestJsonBody teamCreationRequestJsonBody) {


        List<Game> gameList = (List<Game>) gameRepository.findAll();

        List<Team> teamList = (List<Team>) teamRepository.findAll();

        Set<Team> teamSet = new HashSet<Team>(teamList);


        for(Game game : gameList) {
        }


        return new ResponseEntity<>("Team is created successfully", HttpStatus.CREATED);
    }
}
