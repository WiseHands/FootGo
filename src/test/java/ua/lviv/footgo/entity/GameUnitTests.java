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

    private static final String TEAM_A_NAME = "ToniTeamA";

    private static final String TEAM_A_CAPTAIN_NAME = "CapA";
    private static final String TEAM_A_CAPTAIN_PHONE = "CapA@gmail.com";
    private static final String TEAM_A_CAPTAIN_EMAIL = "30666666666";

    private static final String TEAM_A_PLAYER_ONE_NAME = "Player A1";
    private static final String TEAM_A_PLAYER_TWO_NAME = "Player A2";



    private static final String TEAM_B_NAME = "ToniTeamB";

    private static final String TEAM_B_CAPTAIN_NAME = "CapB";
    private static final String TEAM_B_CAPTAIN_PHONE = "CapB@gmail.com";
    private static final String TEAM_B_CAPTAIN_EMAIL = "39666666666";

    private static final String TEAM_B_PLAYER_ONE_NAME = "Player B1";
    private static final String TEAM_B_PLAYER_TWO_NAME = "Player B2";

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

	@Test
    public void testTeamRelation() {
        Team teamA = new Team();
        teamA.setTeamName(TEAM_A_NAME);

        Captain captainA = new Captain();
        captainA.setCaptainName(TEAM_A_CAPTAIN_NAME);
        captainA.setCaptainEmail(TEAM_A_CAPTAIN_EMAIL);
        captainA.setCaptainPhone(TEAM_A_CAPTAIN_PHONE);

        captainA.setTeam(teamA);
        teamA.setCaptain(captainA);

        Player player = new Player();
        player.setPlayerName(TEAM_A_PLAYER_ONE_NAME);
        player.setTeam(teamA);

        Player player2 = new Player();
        player2.setPlayerName(TEAM_A_PLAYER_TWO_NAME);
        player2.setTeam(teamA);

        teamA.addPlayer(player);
        teamA.addPlayer(player2);

        entityManager.persist(captainA);
        entityManager.persist(teamA);
        entityManager.flush();





        Team teamB = new Team();
        teamB.setTeamName(TEAM_B_NAME);

        Captain captainB = new Captain();
        captainB.setCaptainName(TEAM_B_CAPTAIN_NAME);
        captainB.setCaptainEmail(TEAM_B_CAPTAIN_EMAIL);
        captainB.setCaptainPhone(TEAM_B_CAPTAIN_PHONE);

        captainB.setTeam(teamB);
        teamB.setCaptain(captainB);

        player = new Player();
        player.setPlayerName(TEAM_B_PLAYER_ONE_NAME);
        player.setTeam(teamB);

        player2 = new Player();
        player2.setPlayerName(TEAM_B_PLAYER_TWO_NAME);
        player2.setTeam(teamB);

        teamB.addPlayer(player);
        teamB.addPlayer(player2);

        entityManager.persist(captainB);
        entityManager.persist(teamB);
        entityManager.flush();

        Game game = new Game();
        game.setFirstTeam(teamA);
        game.setSecondTeam(teamB);
        gameRepository.save(game);

        List<Game> gameListByFirstTeam = gameRepository.findByFirstTeam(teamA);
        assertThat(gameListByFirstTeam.size()).isEqualTo(1);

        Game gameFromDb = gameListByFirstTeam.get(0);
        assertThat(gameFromDb.getFirstTeam().getTeamName()).isEqualTo(teamA.getTeamName());
        assertThat(gameFromDb.getFirstTeam().getPlayers().size()).isEqualTo(teamA.getPlayers().size());
        assertThat(gameFromDb.getFirstTeam().getCaptain().getCaptainName()).isEqualTo(teamA.getCaptain().getCaptainName());

        assertThat(gameFromDb.getSecondTeam().getTeamName()).isEqualTo(teamB.getTeamName());
        assertThat(gameFromDb.getSecondTeam().getPlayers().size()).isEqualTo(teamB.getPlayers().size());
        assertThat(gameFromDb.getSecondTeam().getCaptain().getCaptainName()).isEqualTo(teamB.getCaptain().getCaptainName());
    }

	@After
	public void cleanUp() {
		gameRepository.deleteAll();
	}
	
}
