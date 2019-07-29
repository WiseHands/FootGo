package com.dev2qa.footgo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.dev2qa.footgo.entity.Team;

public interface UserAccountRepository extends CrudRepository<Team, Long> {

    /*
     * Get user list by user name. Please note the format should be
     * findBy<column_name>.
     */
    List<Team> findByUsername(String username);

    /*
     * Get user list by user name and password. Please note the format should be
     * findBy<column_name_1>And<column_name_2>.
     */
    List<Team> findByUsernameAndPassword(String username, String password);

    @Transactional
    void deleteByUsernameAndPassword(String username, String password);

    @Transactional
    void deleteByUsername(String username);

}