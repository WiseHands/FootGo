package ua.lviv.footgo.entity;

import ua.lviv.footgo.repository.CaptainRepository;
import ua.lviv.footgo.repository.PlayerRepository;
import ua.lviv.footgo.repository.TeamRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TeamUnitTests {

	private static final String CAPTAIN_NAME = "Cap";
	private static final String CAPTAIN_PHONE = "Cap@gmail.com";
	private static final String CAPTAIN_EMAIL = "30666666666";

	private static final String TEAM_NAME = "ToniTeam";

    private static final String PLAYER_ONE_FIRST_NAME = "Player";
    private static final String PLAYER_ONE_LAST_NAME = "P1";
    private static final String PLAYER_TWO_FIRST_NAME = "Player";
    private static final String PLAYER_TWO_LAST_NAME = "P2";



	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private CaptainRepository captainRepository;

    @Autowired
    private PlayerRepository playerRepository;

	@Test
	public void checkIsSomePropertiesInDataBase() {
		// given
		Team team = new Team();
		team.setTeamName(TEAM_NAME);
		entityManager.persist(team);
		entityManager.flush();

		// when
		List<Team> teamFromDataStore = teamRepository.findByTeamName(team.getTeamName());

		// then
		assertThat(teamFromDataStore.get(0).getTeamName())
				.isEqualTo(team.getTeamName());

		
	}

	@Test
	public void checkTeamCaptainRelation() {
		// given
		Team team = new Team();
		team.setTeamName(TEAM_NAME);

		Captain captain = new Captain();
		captain.setCaptainName(CAPTAIN_NAME);
		captain.setCaptainEmail(CAPTAIN_EMAIL);
		captain.setCaptainPhone(CAPTAIN_PHONE);

		captain.setTeam(team);
		team.setCaptain(captain);

		entityManager.persist(team);
		entityManager.persist(captain);
		entityManager.flush();

		// when
		List<Team> teamFromDataStore = teamRepository.findByTeamName(team.getTeamName());

		// then
		assertThat(teamFromDataStore.get(0).getTeamName())
				.isEqualTo(team.getTeamName());

		assertThat(teamFromDataStore.get(0).getCaptain().getCaptainEmail()).isEqualTo(team.getCaptain().getCaptainEmail());
		assertThat(teamFromDataStore.get(0).getCaptain().getCaptainPhone()).isEqualTo(team.getCaptain().getCaptainPhone());
		assertThat(teamFromDataStore.get(0).getCaptain().getCaptainName()).isEqualTo(team.getCaptain().getCaptainName());
	}

	@Test
	public void testCaptainDeletion() {
		Team team = new Team();
		team.setTeamName(TEAM_NAME);

		Captain captain = new Captain();
		captain.setCaptainName(CAPTAIN_NAME);
		captain.setCaptainEmail(CAPTAIN_EMAIL);
		captain.setCaptainPhone(CAPTAIN_PHONE);

		captain.setTeam(team);
		team.setCaptain(captain);

		entityManager.persist(team);
		captain = entityManager.persist(captain);
		entityManager.flush();


		team.setCaptain(null);
		teamRepository.save(team);
		captainRepository.deleteById(captain.getId());

		Optional<Captain> captainFromDb = captainRepository.findById(captain.getId());

		assertThat(captainFromDb.isPresent()).isFalse();
		assertThat(teamRepository.count()).isEqualTo(1);
	}

	@Test
    public void testPlayerListRelation() {
        Team team = new Team();
        team.setTeamName(TEAM_NAME);

        Player player = new Player();
        player.setFirstName(PLAYER_ONE_FIRST_NAME);
        player.setLastName(PLAYER_ONE_LAST_NAME);
        player.setTeam(team);

        Player player2 = new Player();
        player2.setFirstName(PLAYER_TWO_FIRST_NAME);
        player2.setLastName(PLAYER_TWO_LAST_NAME);
        player2.setTeam(team);

		team.addPlayer(player);
		team.addPlayer(player2);

        entityManager.persist(team);
        entityManager.flush();

        List<Player> playersFromDB = playerRepository.findByTeam(team);
        assertThat(playersFromDB.size()).isEqualTo(2);
    }


    @Test
    public void testPlayerListPlayerDeletion() {
        Team team = new Team();
        team.setTeamName(TEAM_NAME);

        Player player = new Player();
        player.setFirstName(PLAYER_ONE_FIRST_NAME);
        player.setLastName(PLAYER_ONE_LAST_NAME);
        player.setTeam(team);

        Player player2 = new Player();
        player2.setFirstName(PLAYER_TWO_FIRST_NAME);
        player2.setLastName(PLAYER_TWO_LAST_NAME);
        player2.setTeam(team);

        team.addPlayer(player);
        team.addPlayer(player2);

        entityManager.persist(team);
        entityManager.flush();

		team.setPlayers(null);
		playerRepository.deleteById(player.getId());
        playerRepository.deleteById(player2.getId());
        teamRepository.save(team);

        List<Player> playersFromDB = playerRepository.findByTeam(team);
        assertThat(playersFromDB.size()).isEqualTo(0);
    }

	@Test
	public void testPlayerListPlayerPartialDeletion() {
		Team team = new Team();
		team.setTeamName(TEAM_NAME);

		Player player = new Player();
		player.setFirstName(PLAYER_ONE_FIRST_NAME);
		player.setLastName(PLAYER_ONE_LAST_NAME);
		player.setTeam(team);

		Player player2 = new Player();
		player2.setFirstName(PLAYER_TWO_FIRST_NAME);
		player2.setLastName(PLAYER_TWO_LAST_NAME);
		player2.setTeam(team);

		List<Player> playerList = new ArrayList();
		playerList.add(player);
		playerList.add(player2);
		team.setPlayers(playerList);

		entityManager.persist(team);
		entityManager.flush();


		playerRepository.deleteById(player.getId());
		team.removePlayer(player);
		teamRepository.save(team);

		List<Player> playersFromDB = playerRepository.findByTeam(team);
		assertThat(playersFromDB.size()).isEqualTo(1);
	}

	@Test
	public void testTeamDeletion() {
		Team team = new Team();
		team.setTeamName(TEAM_NAME);

		Captain captain = new Captain();
		captain.setCaptainName(CAPTAIN_NAME);
		captain.setCaptainEmail(CAPTAIN_EMAIL);
		captain.setCaptainPhone(CAPTAIN_PHONE);

		captain.setTeam(team);
		team.setCaptain(captain);

		entityManager.persist(team);
		captain = entityManager.persist(captain);

		Player player = new Player();
		player.setFirstName(PLAYER_ONE_FIRST_NAME);
		player.setLastName(PLAYER_ONE_LAST_NAME);
		player.setTeam(team);

		Player player2 = new Player();
		player2.setFirstName(PLAYER_TWO_FIRST_NAME);
		player2.setLastName(PLAYER_TWO_LAST_NAME);
		player2.setTeam(team);

		team.addPlayer(player);
		team.addPlayer(player2);

		team = entityManager.persist(team);
		entityManager.flush();

		captainRepository.deleteById(captain.getId());
		teamRepository.deleteById(team.getId());
		Optional<Team> teamAfterDeletionOptional = teamRepository.findById(team.getId());
		assertThat(teamAfterDeletionOptional.isPresent()).isFalse();

		List<Player> playersFromDB = playerRepository.findByTeam(team);
		assertThat(playersFromDB.size()).isEqualTo(0);

		Optional<Captain> captainOptional = captainRepository.findById(captain.getId());
		assertThat(captainOptional.isPresent()).isFalse();
	}


	@After
	public void cleanUp() {
		teamRepository.deleteAll();
		captainRepository.deleteAll();
	}
	
}
