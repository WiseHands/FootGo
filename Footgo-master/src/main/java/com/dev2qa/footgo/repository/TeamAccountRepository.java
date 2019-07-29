package com.dev2qa.footgo.repository;

import com.dev2qa.footgo.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TeamAccountRepository extends CrudRepository<Team, Long> {

//    List<Team> findByCaptainName(String captain_name);

    @Transactional
    void deleteByCaptainName(String captain_name);

}
