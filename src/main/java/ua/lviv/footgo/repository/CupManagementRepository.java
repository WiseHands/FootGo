package ua.lviv.footgo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ua.lviv.footgo.entity.Cup;

public interface CupManagementRepository extends CrudRepository<Cup, Long> {

    @Transactional
    void deleteAll();
}
