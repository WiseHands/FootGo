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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GameUnitTests {

	public static final String TIME = "1920 010203";
	public static final String LOCATION = "FC SOKIL";

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private GameRepository gameRepository;

	@Test
	public void testBasicProperties() {
		Game game = new Game();
		game.setGameTime(TIME);
		game.setLocation(LOCATION);

		game = entityManager.persist(game);
		entityManager.flush();

		Optional<Game> gameFromDBOptional = gameRepository.findById(game.getId());
		assertThat(gameFromDBOptional.isPresent()).isTrue();

		Game gameFromDB = gameFromDBOptional.get();
		assertThat(gameFromDB.getGameTime()).isEqualTo(TIME);
		assertThat(gameFromDB.getLocation()).isEqualTo(LOCATION);
	}

	@After
	public void cleanUp() {
		gameRepository.deleteAll();
	}
	
}
