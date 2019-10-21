package ua.lviv.footgo.repository;

import ua.lviv.footgo.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.footgo.entity.Team;

import java.util.List;

public interface GameRepository extends CrudRepository<Game, Long> {

    List<Game> findByFirstTeam(Team firstTeam);

    List<Game> findBySecondTeam(Team secondTeam);


    List<Game> findByFirstTeamAndIsCompleted(Team firstTeam, boolean isCompleted);

    List<Game> findBySecondTeamAndIsCompleted(Team secondTeam, boolean isCompleted);

    List<Game> findByFirstTeamAndSecondTeam(Team firstTeam, Team secondTeam);

    @Transactional
    void deleteAll();
}
