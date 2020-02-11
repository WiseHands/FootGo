
package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.*;
import ua.lviv.footgo.repository.CardRepository;
import ua.lviv.footgo.repository.GameRepository;
import ua.lviv.footgo.repository.GoalRepository;
import ua.lviv.footgo.repository.TeamRepository;

import java.util.ArrayList;
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

    @Autowired
    CardRepository cardRepository;

    @DeleteMapping(value = "/clear", consumes = "application/json", produces = "application/json")
    public void clear() {
        Iterable<Game> gameList = gameRepository.findAll();
        for (Game game : gameList) {
            game.getTeamAGoals().clear();
            game.getTeamBGoals().clear();
            gameRepository.save(game);
        }
        goalRepository.deleteAll();

    }

    @GetMapping("/{id}")
    public Game getTeam(@PathVariable Long id) {
        return gameRepository.findById(id).get();
    }


    @GetMapping(value = "/all", consumes = "application/json", produces = "application/json")
    public List<Game> getResults() {
        return (List<Game>) gameRepository.findAll();
    }

    @GetMapping(value = "/team", consumes = "application/json", produces = "application/json")
    public List<Game> getResults(@RequestParam Long teamId) {
        Team team = teamRepository.findById(teamId).get();
        List<Game> homeGames = gameRepository.findByFirstTeamAndIsCompleted(team, true);
        List<Game> guestGames = gameRepository.findBySecondTeamAndIsCompleted(team, true);
        List<Game> allGames = Stream.concat(homeGames.stream(), guestGames.stream())
                .collect(Collectors.toList());
        Collections.sort(allGames, new Game.SortByTour());
        return allGames;
    }

    @PostMapping(value = "/{id}/goal", consumes = "application/json", produces = "application/json")
    public Goal addGoal(@PathVariable Long id, @RequestParam Player playerId, @RequestParam int goalMinute, @RequestParam int goalVideoSec, @RequestParam boolean homeTeamGoal) {
        Game game = gameRepository.findById(id).get();
        Goal goal = new Goal();
        goal.setPlayer(playerId);
        goal.setTime(goalMinute);
        goal.setVideoSeconds(goalVideoSec);
        goal.setGame(game);
        if (homeTeamGoal) {
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
        if (isHomeTeamGoal) {
            game.removeGoalForFirstTeam(goal);
        } else {
            game.removeGoalForSecondTeam(goal);
        }
        gameRepository.save(game);
        goalRepository.delete(goal);
    }

    @PostMapping(value = "/{id}/card", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addCard(@PathVariable Long id, @RequestParam Player player, @RequestParam int cardMinute, @RequestParam Card.CardType cardType, @RequestParam boolean homeTeamCard) {
        Game game = gameRepository.findById(id).get();
        boolean canAdd = checkIfCardCanBeAdded(game, cardType, player, homeTeamCard);
        if (canAdd) {
            Card card = new Card();
            card.setPlayer(player);
            card.setCard(cardType);
            card.setTime(cardMinute);
            card.setGame(game);
            if (homeTeamCard) {
                game.addCardForFirstTeamPlayer(card);
            } else {
                game.addCardForSecondTeamPlayer(card);
            }
            cardRepository.save(card);
            gameRepository.save(game);
            return ResponseEntity.ok().body("");
        } else {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("too many cards");
        }
    }
    private boolean checkIfCardCanBeAdded(Game game, Card.CardType cardType, Player player, boolean homeTeamCard) {
        if (cardType.equals(Card.CardType.YELLOW)) {
            List<Card> cardList = new ArrayList<Card>();
            if (homeTeamCard) {
                cardList = game.getTeamACards();
            } else {
                cardList = game.getTeamBCards();
            }

            int howManyYellowCardsPlayerHas = 0;
            for (Card card : cardList) {
                if (card.getPlayer().getId().equals(player.getId())) {
                   howManyYellowCardsPlayerHas += 1;
                }
            }

            return howManyYellowCardsPlayerHas < 2;
        }
        else if (cardType.equals(Card.CardType.RED)) {
            List<Card> cardList = new ArrayList<Card>();
            if (homeTeamCard) {
                cardList = game.getTeamACards();
            } else {
                cardList = game.getTeamBCards();
            }

            int howManyRedCardsPlayerHas = 0;
            for (Card card : cardList) {
                if (card.getPlayer().getId().equals(player.getId())) {
                    howManyRedCardsPlayerHas += 1;
                }
            }

            return howManyRedCardsPlayerHas < 1;
        }
        return true;
    }

    @DeleteMapping(value = "/{gameId}/card/{cardId}", consumes = "application/json", produces = "application/json")
    public void deleteCard(@PathVariable Long cardId, @PathVariable Long gameId, @RequestParam Boolean isHomeTeamCard) {
        Game game = gameRepository.findById(gameId).get();
        Card card = cardRepository.findById(cardId).get();
        if (isHomeTeamCard) {
            game.removeCardForFirstTeam(card);
        } else {
            game.removeCardForSecondTeam(card);
        }
        gameRepository.save(game);
        cardRepository.delete(card);
    }

    @PostMapping(value = "/{gameId}/completed/{isCompleted}", consumes = "application/json", produces = "application/json")
    public void markCompleted(@PathVariable Long gameId, @PathVariable boolean isCompleted) {
        Game game = gameRepository.findById(gameId).get();
        game.setCompleted(isCompleted);
        gameRepository.save(game);
    }

    @PostMapping(value = "/{gameId}/setgametime/{timeGame}", consumes = "application/json", produces = "application/json")
    public void setGameTime(@PathVariable Long gameId, @PathVariable String timeGame) {
        Game game = gameRepository.findById(gameId).get();
        game.setGameTime(timeGame);
        gameRepository.save(game);
    }

    @PutMapping(value = "/{gameId}/setvideourl", consumes = "application/json", produces = "application/json")
    public void setVideoUrl(@PathVariable Long gameId, @RequestParam String videoUrl) {
        Game game = gameRepository.findById(gameId).get();
        game.setVideoUrl(videoUrl);
        gameRepository.save(game);
    }

    @PutMapping(value = "/{gameId}/setdesctext", consumes = "application/json", produces = "application/json")
    public void setDescriptionText(@PathVariable Long gameId, @RequestParam String descText) {
        Game game = gameRepository.findById(gameId).get();
        game.setDescriptionText(descText);
        gameRepository.save(game);
    }

    @PutMapping(value = "/{gameId}/technicaldefeat/{teamId}", consumes = "application/json", produces = "application/json")
    public void setTeamATechnicalDefeat(@PathVariable Long gameId, @PathVariable Long teamId) {
        Game game = gameRepository.findById(gameId).get();

        if(game.getFirstTeam().getId().equals(teamId)) {
            game.setTeamATechnicalDefeat();
        } else {
            game.setTeamBTechnicalDefeat();
        }
        gameRepository.save(game);
    }

    @DeleteMapping(value = "/{gameId}/technicaldefeat", consumes = "application/json", produces = "application/json")
    public void deleteTechnicalDefeat(@PathVariable Long gameId) {
        Game game = gameRepository.findById(gameId).get();
        game.clearTechnicalDefeat();
        gameRepository.save(game);
    }

}
