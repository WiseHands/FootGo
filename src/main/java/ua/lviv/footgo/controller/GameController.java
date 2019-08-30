
package ua.lviv.footgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

@RestController
@RequestMapping(path = "/game")
public class GameController {

    @Autowired
    GameRepository gameRepository;


    @GetMapping("/{id}")
    public Game getTeam(@PathVariable Long id) {
        Game game = gameRepository.findById(id).get();
        return game;

    }
}
