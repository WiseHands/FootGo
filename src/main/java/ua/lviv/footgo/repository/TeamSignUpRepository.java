package ua.lviv.footgo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.entity.TeamSignUpRequest;

import java.util.List;

@Repository
public interface TeamSignUpRepository extends CrudRepository<TeamSignUpRequest, Long> {

    TeamSignUpRequest findByTeamName(String teamName);
    TeamSignUpRequest findByCaptainName(String captainName);
    TeamSignUpRequest findByCaptainPhone(String captainPhone);
    TeamSignUpRequest findByCaptainEmail(String captainEmail);
    TeamSignUpRequest findByPlayerList(String playerList);


}
