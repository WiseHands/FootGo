
package ua.lviv.footgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.footgo.entity.Captain;
import ua.lviv.footgo.entity.Game;
import ua.lviv.footgo.entity.Player;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.jsonmapper.FootballPlayer;
import ua.lviv.footgo.jsonmapper.TeamCreationRequestJsonBody;
import ua.lviv.footgo.jsonmapper.TeamResults;
import ua.lviv.footgo.repository.CaptainRepository;
import ua.lviv.footgo.repository.GameRepository;
import ua.lviv.footgo.repository.PlayerRepository;
import ua.lviv.footgo.repository.TeamRepository;
import ua.lviv.footgo.service.ResultService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/games")
public class ResultController {

    @Autowired
    ResultService resultService;


    @RequestMapping(value = "/table", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public List<TeamResults> buildResultsTable() {
        return resultService.getResults();
    }


}
