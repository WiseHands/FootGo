package com.dev2qa.footgo.entity;

import com.dev2qa.footgo.repository.PlayerRepository;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PlayerUnitTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private PlayerRepository playerRepository;

	@Test
	public void whenFindByName_thenReturnEmployee() {
		// given
		Player player = new Player();
		player.setPlayerName("Toni");
		entityManager.persist(player);
		entityManager.flush();

		// when
		Player foundPlayer = playerRepository.findByPlayerName(player.getPlayerName());

		// then
		assertThat(foundPlayer.getPlayerName())
				.isEqualTo(player.getPlayerName());

		
	}

	@After
	public void cleanUp() {
		playerRepository.deleteAll();
	}
	
}
