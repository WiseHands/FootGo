package ua.lviv.footgo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.footgo.entity.Season;

public interface SeasonRepository extends CrudRepository<Season, Long> {

    @Transactional
    void deleteAll();
}
