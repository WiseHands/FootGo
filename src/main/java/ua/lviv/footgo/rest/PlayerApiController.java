package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.Player;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.repository.PlayerRepository;
import ua.lviv.footgo.repository.TeamRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/player")
public class PlayerApiController {

    @Autowired
    PlayerRepository playerRepository;

    @GetMapping(value = "/all", consumes = "application/json", produces = "application/json")
    public List<Player> getAll() {
        List<Player> requests = (List<Player>) playerRepository.findAll();
        return requests;
    }

    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public Player getById(@PathVariable Long id) {
        Player request = playerRepository.findById(id).get();
        return request;
    }
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public Player update(@PathVariable Long id, @RequestParam String name, String phone, String email) {
        Player player = playerRepository.findById(id).get();
        player.setPlayerName(name);
        player.setPhone(phone);
        player.setEmail(email);
        playerRepository.save(player);
        return player;
    }
}