package ua.lviv.footgo.repository;

import org.springframework.data.repository.CrudRepository;
import ua.lviv.footgo.entity.Penalty;
import ua.lviv.footgo.entity.Player;

import javax.transaction.Transactional;
import java.util.List;

public interface PenaltyRepository extends CrudRepository<Penalty, Long>  {
    List<Penalty> findByPlayer(Player player);
    List<Penalty> findByGameId(Long gameId);

    @Transactional
    void deleteAll();

}
