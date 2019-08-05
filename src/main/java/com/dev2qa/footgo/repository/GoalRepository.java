package com.dev2qa.footgo.repository;

import com.dev2qa.footgo.entity.Goal;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface GoalRepository extends CrudRepository<Goal, Long> {

    Goal findByTime (Integer time);

    @Transactional
    void deleteAll();

}
