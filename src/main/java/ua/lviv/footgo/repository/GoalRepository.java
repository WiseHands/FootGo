package ua.lviv.footgo.repository;

import ua.lviv.footgo.entity.Goal;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface GoalRepository extends CrudRepository<Goal, Long> {

    Goal findByTime (Integer time);

    @Transactional
    void deleteAll();

}
