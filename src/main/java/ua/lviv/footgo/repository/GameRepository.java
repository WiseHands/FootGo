package ua.lviv.footgo.repository;

import ua.lviv.footgo.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GameRepository extends CrudRepository<Game, Long> {

    List<Game> findByTour(Integer nameTour);

    @Transactional
    void deleteAll();
}
