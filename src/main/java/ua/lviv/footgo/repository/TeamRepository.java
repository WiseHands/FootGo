package ua.lviv.footgo.repository;

import ua.lviv.footgo.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

//    Team findByTeamName(String name);

    List<Team> findByTeamName(String team_name);
//    List<Team> findByCaptainName(String captain_name);


}
