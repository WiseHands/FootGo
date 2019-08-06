package ua.lviv.footgo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.lviv.footgo.entity.Game;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.repository.GameRepository;
import ua.lviv.footgo.repository.TeamRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ResultsGeneraor {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    GameRepository gameRepository;



}
