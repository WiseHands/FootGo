package ua.lviv.footgo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.footgo.entity.League;

public interface LeagueManagementRepository extends CrudRepository<League, Long> {

    @Transactional
    void deleteAll();
}
