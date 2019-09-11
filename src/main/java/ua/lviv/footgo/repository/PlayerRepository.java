package ua.lviv.footgo.repository;

import ua.lviv.footgo.entity.Player;
import org.springframework.data.repository.CrudRepository;
import ua.lviv.footgo.entity.Team;

import javax.transaction.Transactional;
import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Long> {

    Player findByFirstName(String firstName);
    Player findByLastName(String lastName);

    @Transactional
    void deleteAll();
    /*
     * Get user list by user name. Please note the format should be
     * findBy<column_name>.
     */
    List<Player> findByTeam(Team team);

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

