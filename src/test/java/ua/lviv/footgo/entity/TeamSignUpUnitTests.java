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
		TeamSignUpRequest team = new TeamSignUpRequest();
		team.setTeamName("ToniTeam");
		entityManager.persist(team);
		entityManager.flush();

		// when
		TeamSignUpRequest teamFromDataStore = teamSignUpRepository.findByTeamName(team.getTeamName());

		// then
		assertThat(teamFromDataStore.getTeamName())
				.isEqualTo(team.getTeamName());

		
	}

	@After
	public void cleanUp() {
		teamSignUpRepository.deleteAll();
	}
	
}
