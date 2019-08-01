package com.dev2qa.footgo.repository;

import com.dev2qa.footgo.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface TeamAccountRepository extends CrudRepository<Team, Long> {
    List<Team> findByTeamName(String team_name);
//    List<Team> findByCaptainName(String captain_name);

    @Transactional
    void deleteByCaptainName(String captain_name);
}
