package ua.lviv.footgo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.footgo.entity.Game;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.entity.Tour;

import java.util.List;

public interface TourRepository extends CrudRepository<Tour, Long> {

    @Transactional
    void deleteAll();
}
