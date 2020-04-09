package ua.lviv.footgo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.footgo.entity.Season;
import ua.lviv.footgo.entity.Tournament;

public interface TournamentRepository extends CrudRepository<Tournament, Long> {

    @Transactional
    void deleteAll();
}
