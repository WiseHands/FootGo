package ua.lviv.footgo.repository;

import ua.lviv.footgo.entity.Captain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CaptainRepository extends CrudRepository<Captain, Long> {

    Captain findByCaptainName(String name);

    @Transactional
    void deleteByCaptainEmail(String captainEmail);

    @Transactional
    void deleteById(Long id);
}
