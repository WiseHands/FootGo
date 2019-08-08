package ua.lviv.footgo.entity;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ua.lviv.footgo.repository.TeamRepository;
import ua.lviv.footgo.repository.TeamSignUpRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TeamSignUpUnitTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TeamSignUpRepository teamSignUpRepository;

	@Test
	public void checkIsSomePropertiesInDataBase() {
		// given
		TeamSignUpRequest teamSignUpRequest = new TeamSignUpRequest();
		teamSignUpRequest.setTeamName("ToniTeam");
		teamSignUpRequest.setCaptainName("toni");
		teamSignUpRequest.setCaptainPhone("380965658");
		teamSignUpRequest.setCaptainEmail("teamSignUpRequest@mail");
		teamSignUpRequest.setPlayerList("1,3,4");
		entityManager.persist(teamSignUpRequest);
		entityManager.flush();

		// when
		TeamSignUpRequest teamFrom = teamSignUpRepository.findByTeamName(teamSignUpRequest.getTeamName());
		TeamSignUpRequest teamFromData = teamSignUpRepository.findByCaptainName(teamSignUpRequest.getCaptainName());
		TeamSignUpRequest teamFromDataS = teamSignUpRepository.findByCaptainPhone(teamSignUpRequest.getCaptainPhone());
		TeamSignUpRequest teamFromDataSt = teamSignUpRepository.findByCaptainEmail(teamSignUpRequest.getCaptainEmail());
		TeamSignUpRequest teamFromDataStore = teamSignUpRepository.findByPlayerList(teamSignUpRequest.getPlayerList());

		// then
		assertThat(teamFrom.getTeamName()).isEqualTo(teamSignUpRequest.getTeamName());
		assertThat(teamFromData.getCaptainName()).isEqualTo(teamSignUpRequest.getCaptainName());
		assertThat(teamFromDataS.getCaptainPhone()).isEqualTo(teamSignUpRequest.getCaptainPhone());
		assertThat(teamFromDataSt.getCaptainEmail()).isEqualTo(teamSignUpRequest.getCaptainEmail());
		assertThat(teamFromDataStore.getPlayerList()).isEqualTo(teamSignUpRequest.getPlayerList());

		
	}

	@After
	public void cleanUp() {
		teamSignUpRepository.deleteAll();
	}
	
}
