package com.dev2qa.footgo.repository;

import com.dev2qa.footgo.entity.Captain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CaptainRepository extends CrudRepository<Captain, Long> {

    Captain findByCaptainName(String name);

    @Transactional
    void deleteByCaptainName(String captain_name);
}
