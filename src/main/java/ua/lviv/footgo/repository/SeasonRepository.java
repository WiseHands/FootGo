package ua.lviv.footgo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.footgo.entity.Season;

import java.util.List;

public interface SeasonRepository extends CrudRepository<Season, Long> {

/*    @Query("SELECT s FROM Season s WHERE s.id <> :activeSeasonId ORDER BY s.id ASC")
    List<Season> getSeasonsExceptActive(@Param("activeSeasonId") Long activeSeasonId);*/

    @Transactional
    void deleteAll();
}
