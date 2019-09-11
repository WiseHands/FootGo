package ua.lviv.footgo.entity;

import ua.lviv.footgo.repository.GameRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ua.lviv.footgo.repository.GoalRepository;
import ua.lviv.footgo.repository.TeamRepository;

import java.text.ParseException;
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

    private static final String TEAM_A_PLAYER_ONE_FIRST_NAME = "Player";
    private static final String TEAM_A_PLAYER_ONE_LAST_NAME = "A1";
    private static final String TEAM_A_PLAYER_TWO_FIRST_NAME = "Player";
    private static final String TEAM_A_PLAYER_TWO_LAST_NAME = "A2";

    private static final Integer TEAM_A_GOAL_TIME = 123;



    private static final String TEAM_B_NAME = "ToniTeamB";

    private static final String TEAM_B_CAPTAIN_NAME = "CapB";
    private static final String TEAM_B_CAPTAIN_PHONE = "CapB@gmail.com";
    private static final String TEAM_B_CAPTAIN_EMAIL = "39666666666";

    private static final String TEAM_B_PLAYER_ONE_FIRST_NAME = "Player B1";
    private static final String TEAM_B_PLAYER_ONE_LAST_NAME = "B1";
    private static final String TEAM_B_PLAYER_TWO_FIRST_NAME = "Player B2";
    private static final String TEAM_B_PLAYER_TWO_LAST_NAME = "B2";

    private static final Integer TEAM_B_GOAL_TIME = 136;
    private static final Integer TEAM_B_GOAL_TWO_TIME = 176;


    @Autowired
	private TestEntityManager entityManager;

	@Autowired
	private GameRepository gameRepository;

	@Autowired
    private GoalRepository goalRepository;

    @Autowired
    private TeamRepository teamRepository;

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
        player.setFirstName(TEAM_A_PLAYER_ONE_FIRST_NAME);
        player.setLastName(TEAM_A_PLAYER_ONE_LAST_NAME);
        player.setTeam(teamA);

        Player player2 = new Player();
        player2.setFirstName(TEAM_A_PLAYER_TWO_FIRST_NAME);
        player2.setLastName(TEAM_B_PLAYER_TWO_LAST_NAME);
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
        player.setFirstName(TEAM_B_PLAYER_ONE_FIRST_NAME);
        player.setLastName(TEAM_B_PLAYER_ONE_LAST_NAME);
        player.setTeam(teamB);

        player2 = new Player();
        player2.setFirstName(TEAM_B_PLAYER_TWO_FIRST_NAME);
        player2.setLastName(TEAM_B_PLAYER_TWO_LAST_NAME);
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

        List<Game> gameListByFirstTeam = gameRepository.findByFirstTeamAndIsCompleted(teamA, true);
        assertThat(gameListByFirstTeam.size()).isEqualTo(1);

        Game gameFromDb = gameListByFirstTeam.get(0);
        assertThat(gameFromDb.getFirstTeam().getTeamName()).isEqualTo(teamA.getTeamName());
        assertThat(gameFromDb.getFirstTeam().getPlayers().size()).isEqualTo(teamA.getPlayers().size());
        assertThat(gameFromDb.getFirstTeam().getCaptain().getCaptainName()).isEqualTo(teamA.getCaptain().getCaptainName());

        assertThat(gameFromDb.getSecondTeam().getTeamName()).isEqualTo(teamB.getTeamName());
        assertThat(gameFromDb.getSecondTeam().getPlayers().size()).isEqualTo(teamB.getPlayers().size());
        assertThat(gameFromDb.getSecondTeam().getCaptain().getCaptainName()).isEqualTo(teamB.getCaptain().getCaptainName());
    }

    @Test
    public void testGoalRelation() {
        Team teamA = new Team();
        teamA.setTeamName(TEAM_A_NAME);

        Captain captainA = new Captain();
        captainA.setCaptainName(TEAM_A_CAPTAIN_NAME);
        captainA.setCaptainEmail(TEAM_A_CAPTAIN_EMAIL);
        captainA.setCaptainPhone(TEAM_A_CAPTAIN_PHONE);

        captainA.setTeam(teamA);
        teamA.setCaptain(captainA);

        Player teamAPlayer1 = new Player();
        teamAPlayer1.setFirstName(TEAM_A_PLAYER_ONE_FIRST_NAME);
        teamAPlayer1.setLastName(TEAM_A_PLAYER_ONE_LAST_NAME);
        teamAPlayer1.setTeam(teamA);

        Player teamAplayer2 = new Player();
        teamAplayer2.setFirstName(TEAM_A_PLAYER_TWO_FIRST_NAME);
        teamAplayer2.setLastName(TEAM_A_PLAYER_TWO_LAST_NAME);
        teamAplayer2.setTeam(teamA);

        teamA.addPlayer(teamAPlayer1);
        teamA.addPlayer(teamAplayer2);

        entityManager.persist(captainA);
        entityManager.persist(teamA);
        entityManager.flush();

//        created team B
        Team teamB = new Team();
        teamB.setTeamName(TEAM_B_NAME);

        Captain captainB = new Captain();
        captainB.setCaptainName(TEAM_B_CAPTAIN_NAME);
        captainB.setCaptainEmail(TEAM_B_CAPTAIN_EMAIL);
        captainB.setCaptainPhone(TEAM_B_CAPTAIN_PHONE);

        captainB.setTeam(teamB);
        teamB.setCaptain(captainB);

        Player teamBplayer = new Player();
        teamBplayer.setFirstName(TEAM_B_PLAYER_ONE_FIRST_NAME);
        teamBplayer.setLastName(TEAM_B_PLAYER_ONE_LAST_NAME);
        teamBplayer.setTeam(teamB);

        Player teamBplayer2 = new Player();
        teamBplayer2.setFirstName(TEAM_B_PLAYER_TWO_FIRST_NAME);
        teamBplayer2.setLastName(TEAM_B_PLAYER_ONE_LAST_NAME);
        teamBplayer2.setTeam(teamB);

        teamB.addPlayer(teamBplayer);
        teamB.addPlayer(teamBplayer2);

        entityManager.persist(captainB);
        entityManager.persist(teamB);
        entityManager.flush();

        Game game = new Game();
        game.setFirstTeam(teamA);
        game.setSecondTeam(teamB);

        Goal goalTeamA = new Goal();
        goalTeamA.setTime(TEAM_A_GOAL_TIME);
        goalTeamA.setPlayer(teamAPlayer1);
        goalTeamA.setGame(game);

        Goal goalTeamB = new Goal();
        goalTeamB.setTime(TEAM_B_GOAL_TIME);
        goalTeamB.setPlayer(teamBplayer);
        goalTeamB.setGame(game);

        game.addGoalForFirstTeam(goalTeamA);
        game.addGoalForSecondTeam(goalTeamB);

        game = gameRepository.save(game);



        Optional<Game> gameFromDBOptional = gameRepository.findById(game.getId());
        assertThat(gameFromDBOptional.isPresent()).isTrue();

        Game gameFromDb = gameFromDBOptional.get();
        assertThat(gameFromDb.getTeamAGoals().get(0).getTime()).isEqualTo(TEAM_A_GOAL_TIME);
        assertThat(gameFromDb.getTeamBGoals().get(0).getTime()).isEqualTo(TEAM_B_GOAL_TIME);
        assertThat(gameFromDb.isADraw()).isEqualTo(true);
        assertThat(gameFromDb.hasTeamAWin()).isEqualTo(false);
        assertThat(gameFromDb.hasTeamBWin()).isEqualTo(false);

    }

    @Test
    public void testGoalRemove() {
        Team teamA = new Team();
        teamA.setTeamName(TEAM_A_NAME);

        Captain captainA = new Captain();
        captainA.setCaptainName(TEAM_A_CAPTAIN_NAME);
        captainA.setCaptainEmail(TEAM_A_CAPTAIN_EMAIL);
        captainA.setCaptainPhone(TEAM_A_CAPTAIN_PHONE);

        captainA.setTeam(teamA);
        teamA.setCaptain(captainA);

        Player teamAPlayer1 = new Player();
        teamAPlayer1.setFirstName(TEAM_A_PLAYER_ONE_FIRST_NAME);
        teamAPlayer1.setLastName(TEAM_A_PLAYER_ONE_LAST_NAME);
        teamAPlayer1.setTeam(teamA);

        Player teamAplayer2 = new Player();
        teamAplayer2.setFirstName(TEAM_A_PLAYER_TWO_FIRST_NAME);
        teamAplayer2.setLastName(TEAM_A_PLAYER_TWO_LAST_NAME);
        teamAplayer2.setTeam(teamA);

        teamA.addPlayer(teamAPlayer1);
        teamA.addPlayer(teamAplayer2);

        entityManager.persist(captainA);
        entityManager.persist(teamA);
        entityManager.flush();

//        created team B
        Team teamB = new Team();
        teamB.setTeamName(TEAM_B_NAME);

        Captain captainB = new Captain();
        captainB.setCaptainName(TEAM_B_CAPTAIN_NAME);
        captainB.setCaptainEmail(TEAM_B_CAPTAIN_EMAIL);
        captainB.setCaptainPhone(TEAM_B_CAPTAIN_PHONE);

        captainB.setTeam(teamB);
        teamB.setCaptain(captainB);

        Player teamBplayer = new Player();
        teamBplayer.setFirstName(TEAM_B_PLAYER_ONE_FIRST_NAME);
        teamBplayer.setLastName(TEAM_B_PLAYER_ONE_LAST_NAME);
        teamBplayer.setTeam(teamB);

        Player teamBplayer2 = new Player();
        teamBplayer2.setFirstName(TEAM_B_PLAYER_TWO_FIRST_NAME);
        teamBplayer2.setLastName(TEAM_B_PLAYER_TWO_LAST_NAME);
        teamBplayer2.setTeam(teamB);

        teamB.addPlayer(teamBplayer);
        teamB.addPlayer(teamBplayer2);

        entityManager.persist(captainB);
        entityManager.persist(teamB);
        entityManager.flush();

//      create game
        Game game = new Game();
        game.setFirstTeam(teamA);
        game.setSecondTeam(teamB);

//      create goal
        Goal goalTeamA = new Goal();
        goalTeamA.setTime(TEAM_A_GOAL_TIME);
        goalTeamA.setPlayer(teamAPlayer1);
        goalTeamA.setGame(game);
//        create goal
        Goal goalTeamB = new Goal();
        goalTeamB.setTime(TEAM_B_GOAL_TIME);
        goalTeamB.setPlayer(teamBplayer);
        goalTeamB.setGame(game);


        game.addGoalForFirstTeam(goalTeamA);
        game.addGoalForSecondTeam(goalTeamB);
        game = gameRepository.save(game);

        Goal goal = game.getTeamAGoals().get(0);
        game.getTeamAGoals().remove(goal);
        game = gameRepository.save(game);


        Optional<Game> gameFromDBOptional = gameRepository.findById(game.getId());
        assertThat(gameFromDBOptional.isPresent()).isTrue();

        Game gameFromDb = gameFromDBOptional.get();
        assertThat(gameFromDb.getTeamAGoals().size()).isEqualTo(0);
        assertThat(gameFromDb.getTeamBGoals().get(0).getTime()).isEqualTo(TEAM_B_GOAL_TIME);
        assertThat(gameFromDb.isADraw()).isEqualTo(false);
        assertThat(gameFromDb.hasTeamAWin()).isEqualTo(false);
        assertThat(gameFromDb.hasTeamBWin()).isEqualTo(true);

    }

    @Test
    public void testWinTeamARelation() {
        Team teamA = new Team();
        teamA.setTeamName(TEAM_A_NAME);

        Captain captainA = new Captain();
        captainA.setCaptainName(TEAM_A_CAPTAIN_NAME);
        captainA.setCaptainEmail(TEAM_A_CAPTAIN_EMAIL);
        captainA.setCaptainPhone(TEAM_A_CAPTAIN_PHONE);

        captainA.setTeam(teamA);
        teamA.setCaptain(captainA);

        Player teamAPlayer1 = new Player();
        teamAPlayer1.setFirstName(TEAM_A_PLAYER_ONE_FIRST_NAME);
        teamAPlayer1.setLastName(TEAM_A_PLAYER_ONE_LAST_NAME);
        teamAPlayer1.setTeam(teamA);

        Player teamAplayer2 = new Player();
        teamAplayer2.setFirstName(TEAM_A_PLAYER_TWO_FIRST_NAME);
        teamAplayer2.setLastName(TEAM_A_PLAYER_TWO_LAST_NAME);
        teamAplayer2.setTeam(teamA);

        teamA.addPlayer(teamAPlayer1);
        teamA.addPlayer(teamAplayer2);

        entityManager.persist(captainA);
        entityManager.persist(teamA);
        entityManager.flush();

//        created team B
        Team teamB = new Team();
        teamB.setTeamName(TEAM_B_NAME);

        Captain captainB = new Captain();
        captainB.setCaptainName(TEAM_B_CAPTAIN_NAME);
        captainB.setCaptainEmail(TEAM_B_CAPTAIN_EMAIL);
        captainB.setCaptainPhone(TEAM_B_CAPTAIN_PHONE);

        captainB.setTeam(teamB);
        teamB.setCaptain(captainB);

        Player teamBplayer = new Player();
        teamBplayer.setFirstName(TEAM_B_PLAYER_ONE_FIRST_NAME);
        teamBplayer.setLastName(TEAM_B_PLAYER_ONE_LAST_NAME);
        teamBplayer.setTeam(teamB);

        Player teamBplayer2 = new Player();
        teamBplayer2.setFirstName(TEAM_B_PLAYER_TWO_FIRST_NAME);
        teamBplayer2.setLastName(TEAM_B_PLAYER_TWO_LAST_NAME);
        teamBplayer2.setTeam(teamB);

        teamB.addPlayer(teamBplayer);
        teamB.addPlayer(teamBplayer2);

        entityManager.persist(captainB);
        entityManager.persist(teamB);
        entityManager.flush();

        Game game = new Game();
        game.setFirstTeam(teamA);
        game.setSecondTeam(teamB);

        Goal goalTeamA = new Goal();
        goalTeamA.setTime(TEAM_A_GOAL_TIME);
        goalTeamA.setPlayer(teamAPlayer1);
        goalTeamA.setGame(game);

        game.addGoalForFirstTeam(goalTeamA);

        game = gameRepository.save(game);


        Optional<Game> gameFromDBOptional = gameRepository.findById(game.getId());
        assertThat(gameFromDBOptional.isPresent()).isTrue();

        Game gameFromDb = gameFromDBOptional.get();
        assertThat(gameFromDb.getTeamAGoals().get(0).getTime()).isEqualTo(TEAM_A_GOAL_TIME);
        assertThat(gameFromDb.hasTeamAWin()).isEqualTo(true);
    }

    @Test
    public void testWinTeamBRelation() {
        Team teamA = new Team();
        teamA.setTeamName(TEAM_A_NAME);

        Captain captainA = new Captain();
        captainA.setCaptainName(TEAM_A_CAPTAIN_NAME);
        captainA.setCaptainEmail(TEAM_A_CAPTAIN_EMAIL);
        captainA.setCaptainPhone(TEAM_A_CAPTAIN_PHONE);

        captainA.setTeam(teamA);
        teamA.setCaptain(captainA);

        Player teamAPlayer1 = new Player();
        teamAPlayer1.setFirstName(TEAM_A_PLAYER_ONE_FIRST_NAME);
        teamAPlayer1.setLastName(TEAM_A_PLAYER_ONE_LAST_NAME);
        teamAPlayer1.setTeam(teamA);

        Player teamAplayer2 = new Player();
        teamAplayer2.setFirstName(TEAM_A_PLAYER_TWO_FIRST_NAME);
        teamAplayer2.setLastName(TEAM_A_PLAYER_TWO_LAST_NAME);
        teamAplayer2.setTeam(teamA);

        teamA.addPlayer(teamAPlayer1);
        teamA.addPlayer(teamAplayer2);

        entityManager.persist(captainA);
        entityManager.persist(teamA);
        entityManager.flush();

//        created team B
        Team teamB = new Team();
        teamB.setTeamName(TEAM_B_NAME);

        Captain captainB = new Captain();
        captainB.setCaptainName(TEAM_B_CAPTAIN_NAME);
        captainB.setCaptainEmail(TEAM_B_CAPTAIN_EMAIL);
        captainB.setCaptainPhone(TEAM_B_CAPTAIN_PHONE);

        captainB.setTeam(teamB);
        teamB.setCaptain(captainB);

        Player teamBplayer = new Player();
        teamBplayer.setFirstName(TEAM_B_PLAYER_ONE_FIRST_NAME);
        teamBplayer.setLastName(TEAM_B_PLAYER_ONE_LAST_NAME);
        teamBplayer.setTeam(teamB);

        Player teamBplayer2 = new Player();
        teamBplayer2.setFirstName(TEAM_B_PLAYER_TWO_FIRST_NAME);
        teamBplayer2.setLastName(TEAM_B_PLAYER_TWO_LAST_NAME);
        teamBplayer2.setTeam(teamB);

        teamB.addPlayer(teamBplayer);
        teamB.addPlayer(teamBplayer2);

        entityManager.persist(captainB);
        entityManager.persist(teamB);
        entityManager.flush();

//        create game
        Game game = new Game();
        game.setFirstTeam(teamA);
        game.setSecondTeam(teamB);

//         create goal for team A
        Goal goalTeamA = new Goal();
        goalTeamA.setTime(TEAM_A_GOAL_TIME);
        goalTeamA.setPlayer(teamAPlayer1);
        goalTeamA.setGame(game);

//         create goal for team B
        Goal goalTeamB = new Goal();
        goalTeamB.setTime(TEAM_B_GOAL_TIME);
        goalTeamB.setPlayer(teamBplayer);
        goalTeamB.setGame(game);

//         create second goal for team B
        Goal goalTeamBTwo = new Goal();
        goalTeamBTwo.setTime(TEAM_B_GOAL_TWO_TIME);
        goalTeamBTwo.setPlayer(teamBplayer);
        goalTeamBTwo.setGame(game);


        game.addGoalForFirstTeam(goalTeamA);
        game.addGoalForSecondTeam(goalTeamB);
        game.addGoalForSecondTeam(goalTeamBTwo);

        game = gameRepository.save(game);


        Optional<Game> gameFromDBOptional = gameRepository.findById(game.getId());
        assertThat(gameFromDBOptional.isPresent()).isTrue();

        Game gameFromDb = gameFromDBOptional.get();
        assertThat(gameFromDb.getTeamAGoals().get(0).getTime()).isEqualTo(TEAM_A_GOAL_TIME);
        assertThat(gameFromDb.getTeamBGoals().get(0).getTime()).isEqualTo(TEAM_B_GOAL_TIME);
        assertThat(gameFromDb.getTeamBGoals().get(1).getTime()).isEqualTo(TEAM_B_GOAL_TWO_TIME);
        assertThat(gameFromDb.hasTeamAWin()).isEqualTo(false);
        assertThat(gameFromDb.hasTeamBWin()).isEqualTo(true);
    }

    @Test
    public void testGameDeletion(){
        Team teamA = new Team();
        teamA.setTeamName(TEAM_A_NAME);

        Captain captainA = new Captain();
        captainA.setCaptainName(TEAM_A_CAPTAIN_NAME);
        captainA.setCaptainEmail(TEAM_A_CAPTAIN_EMAIL);
        captainA.setCaptainPhone(TEAM_A_CAPTAIN_PHONE);

        captainA.setTeam(teamA);
        teamA.setCaptain(captainA);

        Player teamAPlayer1 = new Player();
        teamAPlayer1.setFirstName(TEAM_A_PLAYER_ONE_FIRST_NAME);
        teamAPlayer1.setLastName(TEAM_A_PLAYER_ONE_LAST_NAME);
        teamAPlayer1.setTeam(teamA);

        Player teamAplayer2 = new Player();
        teamAplayer2.setFirstName(TEAM_A_PLAYER_TWO_FIRST_NAME);
        teamAplayer2.setLastName(TEAM_A_PLAYER_TWO_LAST_NAME);
        teamAplayer2.setTeam(teamA);

        teamA.addPlayer(teamAPlayer1);
        teamA.addPlayer(teamAplayer2);

        entityManager.persist(captainA);
        entityManager.persist(teamA);
        entityManager.flush();

//        created team B
        Team teamB = new Team();
        teamB.setTeamName(TEAM_B_NAME);

        Captain captainB = new Captain();
        captainB.setCaptainName(TEAM_B_CAPTAIN_NAME);
        captainB.setCaptainEmail(TEAM_B_CAPTAIN_EMAIL);
        captainB.setCaptainPhone(TEAM_B_CAPTAIN_PHONE);

        captainB.setTeam(teamB);
        teamB.setCaptain(captainB);

        Player teamBplayer = new Player();
        teamBplayer.setFirstName(TEAM_B_PLAYER_ONE_FIRST_NAME);
        teamBplayer.setLastName(TEAM_B_PLAYER_ONE_LAST_NAME);
        teamBplayer.setTeam(teamB);

        Player teamBplayer2 = new Player();
        teamBplayer2.setFirstName(TEAM_B_PLAYER_TWO_FIRST_NAME);
        teamBplayer2.setLastName(TEAM_B_PLAYER_TWO_LAST_NAME);
        teamBplayer2.setTeam(teamB);

        teamB.addPlayer(teamBplayer);
        teamB.addPlayer(teamBplayer2);

        entityManager.persist(captainB);
        entityManager.persist(teamB);
        entityManager.flush();

//        create game
        Game game = new Game();
        game.setFirstTeam(teamA);
        game.setSecondTeam(teamB);

//         create goal for team A
        Goal goalTeamA = new Goal();
        goalTeamA.setTime(TEAM_A_GOAL_TIME);
        goalTeamA.setPlayer(teamAPlayer1);
        goalTeamA.setGame(game);

//         create goal for team B
        Goal goalTeamB = new Goal();
        goalTeamB.setTime(TEAM_B_GOAL_TIME);
        goalTeamB.setPlayer(teamBplayer);
        goalTeamB.setGame(game);

        Goal goalTeamBTwo = new Goal();
        goalTeamBTwo.setTime(TEAM_B_GOAL_TWO_TIME);
        goalTeamBTwo.setPlayer(teamBplayer);
        goalTeamBTwo.setGame(game);


        game.addGoalForFirstTeam(goalTeamA);
        game.addGoalForSecondTeam(goalTeamB);
        game.addGoalForSecondTeam(goalTeamBTwo);

        game = gameRepository.save(game);


        Optional<Game> gameFromDBOptional = gameRepository.findById(game.getId());
        assertThat(gameFromDBOptional.isPresent()).isTrue();

        gameRepository.deleteById(gameFromDBOptional.get().getId());

        gameFromDBOptional = gameRepository.findById(game.getId());
        assertThat(gameFromDBOptional.isPresent()).isFalse();

        Optional<Team> teamAFromDB = teamRepository.findById(teamA.getId());
        assertThat(teamAFromDB.isPresent()).isTrue();

        Optional<Team> teamBFromDB = teamRepository.findById(teamB.getId());
        assertThat(teamBFromDB.isPresent()).isTrue();

    }

    @Test
    public void convertTime() throws ParseException {
	    Game game = new Game();
	    String time = "2019-09-11T02:00:00.000Z";
	    game.setGameTime(time);

        assertThat(game.formatTime()).isEqualTo("Saturday, Jan 28");

    }

	@After
	public void cleanUp() {
		gameRepository.deleteAll();
	}
	
}
