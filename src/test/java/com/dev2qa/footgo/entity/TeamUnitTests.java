package com.dev2qa.footgo.entity;

import com.dev2qa.footgo.repository.TeamRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TeamUnitTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TeamRepository teamRepository;

	@Test
	public void checkIsSomePropertiesInDataBase() {
		// given
		Team team = new Team();
		team.setTeamName("ToniTeam");
		entityManager.persist(team);
		entityManager.flush();

		// when
		List<Team> teamFromDataStore = teamRepository.findByTeamName(team.getTeamName());

		// then
		assertThat(teamFromDataStore.get(0).getTeamName())
				.isEqualTo(team.getTeamName());

		
	}

	@After
	public void cleanUp() {
		teamRepository.deleteAll();
	}
	
}
