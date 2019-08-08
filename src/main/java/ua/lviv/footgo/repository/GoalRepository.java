package ua.lviv.footgo.repository;

import ua.lviv.footgo.entity.Goal;
import org.springframework.data.repository.CrudRepository;
import ua.lviv.footgo.entity.Player;

import javax.transaction.Transactional;
import java.util.List;

public interface GoalRepository extends CrudRepository<Goal, Long> {


    List<Goal> findByPlayer(Player player);
    Goal findByTime (Integer time);

    @Transactional
    void deleteAll();

}
