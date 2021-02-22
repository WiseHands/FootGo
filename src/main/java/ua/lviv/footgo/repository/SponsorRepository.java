package ua.lviv.footgo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.footgo.entity.Sponsor;
import ua.lviv.footgo.entity.Team;

import java.util.List;

@Repository
public interface SponsorRepository extends CrudRepository<Sponsor, Long> {

    List<Sponsor> findBySponsorName(String sponsor_name);

}
