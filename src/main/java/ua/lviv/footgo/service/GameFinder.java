package ua.lviv.footgo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ua.lviv.footgo.entity.Game;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.repository.GameRepository;

import java.util.List;

@Service
public class GameFinder {

    @Autowired
    GameRepository gameRepository;

    public List<Game> findAllGamesForTeam(Team team) {
        List<Game> gameList = (List<Game>) gameRepository.findByFirstTeam(team);
        gameList.addAll((List<Game>) gameRepository.findBySecondTeam(team));
        return gameList;
    }
}
