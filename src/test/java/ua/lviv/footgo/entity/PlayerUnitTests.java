package ua.lviv.footgo.entity;

import ua.lviv.footgo.repository.PlayerRepository;
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
	public void checkIsSomePropertiesInDataBase() {
		// given
		Player player = new Player();
		player.setFirstName("Roma");
		player.setLastName("Toni");
		entityManager.persist(player);
		entityManager.flush();

		// when
		Player foundPlayerByFirstName = playerRepository.findByFirstName(player.getFirstName());
		Player foundPlayerByLastName =playerRepository.findByLastName(player.getLastName());
		// then
		assertThat(foundPlayerByFirstName.getFirstName())
				.isEqualTo(player.getFirstName());
		assertThat(foundPlayerByLastName.getLastName()).isEqualTo(player.getLastName());
		
	}

	@After
	public void cleanUp() {
		playerRepository.deleteAll();
	}
	
}
