package ua.lviv.footgo.entity;

import ua.lviv.footgo.repository.GameRepository;
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
public class GameUnitTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private GameRepository gameRepository;

	@Test
	public void checkIsSomePropertiesInDataBase() {
		// given
		Game game = new Game();
		game.setTour(1);
		entityManager.persist(game);
		entityManager.flush();

		// when
		List<Game> captainFromDataStore = gameRepository.findByTour(game.getTour());

		// then
		assertThat(captainFromDataStore.get(0).getTour())
				.isEqualTo(game.getTour());

		
	}

	@After
	public void cleanUp() {
		gameRepository.deleteAll();
	}
	
}
