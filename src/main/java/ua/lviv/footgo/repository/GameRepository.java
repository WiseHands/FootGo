package ua.lviv.footgo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.lviv.footgo.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.footgo.entity.Team;

import java.time.OffsetDateTime;
import java.util.List;

public interface GameRepository extends CrudRepository<Game, Long> {

    List<Game> findByFirstTeam(Team firstTeam);

    List<Game> findBySecondTeam(Team secondTeam);

    List<Game> findByFirstTeamAndIsCompleted(Team firstTeam, boolean isCompleted);

    List<Game> findBySecondTeamAndIsCompleted(Team secondTeam, boolean isCompleted);

    List<Game> findByFirstTeamAndSecondTeam(Team firstTeam, Team secondTeam);

    List<Game> findByTourId(Long tour);

    @Query("SELECT g FROM Game g WHERE g.gameTime > :timeStamp ORDER BY g.gameTime ASC")
    List<Game> fetchGameAfterTimeStamp(@Param("timeStamp") OffsetDateTime timeStamp);

    @Transactional
    void deleteAll();

}
