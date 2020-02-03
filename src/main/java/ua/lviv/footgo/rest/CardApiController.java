
package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.Card;
import ua.lviv.footgo.entity.Goal;
import ua.lviv.footgo.entity.Player;
import ua.lviv.footgo.repository.*;

@RestController
@RequestMapping(path = "/api/card")
public class CardApiController {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    CardRepository cardRepository;

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public void editCard(@PathVariable Long id, @RequestParam Long playerId, @RequestParam Card.CardType cardType) {
        Card card = cardRepository.findById(id).get();
        if (playerId != null) {
            Player player = playerRepository.findById(playerId).get();
            card.setPlayer(player);
        }
        if (cardType != null) {
            card.setCard(cardType);
        }
        cardRepository.save(card);
    }

}
