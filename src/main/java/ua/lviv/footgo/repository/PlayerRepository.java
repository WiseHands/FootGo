package ua.lviv.footgo.repository;

import ua.lviv.footgo.entity.Player;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    Player findByPlayerName(String playerName);

    @Transactional
    void deleteAll();
    /*
     * Get user list by user name. Please note the format should be
     * findBy<column_name>.
     */
//    List<Player> findByUsername(String last_name);

    /*
     * Get user list by user name and password. Please note the format should be
     * findBy<column_name_1>And<column_name_2>.
     */
//    List<Team> findByUsernameAndPassword(String username, String password);
//
//    @Transactional
//    void deleteByFirstNameAndLastName(String firstName, String lastName);
//


}

