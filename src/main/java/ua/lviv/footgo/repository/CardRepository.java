package ua.lviv.footgo.repository;

import org.springframework.data.repository.CrudRepository;
import ua.lviv.footgo.entity.Card;
import ua.lviv.footgo.entity.Player;

import javax.transaction.Transactional;
import java.util.List;

public interface CardRepository extends CrudRepository<Card, Long> {

    List<Card> findByPlayer(Player player);
    Card findByCardType(Card.CardType cardType);

    @Transactional
    void deleteAll();

}
