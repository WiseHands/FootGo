package ua.lviv.footgo.entity;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ua.lviv.footgo.repository.GameRepository;
import ua.lviv.footgo.repository.GoalRepository;
import ua.lviv.footgo.repository.TeamRepository;
import ua.lviv.footgo.repository.TourRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TourUnitTests {

	public static final String TIME = "1920 010203";
	public static final String LOCATION = "FC SOKIL";

    private static final String TEAM_A_NAME = "ToniTeamA";

    private static final String TEAM_A_CAPTAIN_NAME = "CapA";
    private static final String TEAM_A_CAPTAIN_PHONE = "CapA@gmail.com";
    private static final String TEAM_A_CAPTAIN_EMAIL = "30666666666";

    private static final String TEAM_A_PLAYER_ONE_NAME = "Player A1";
    private static final String TEAM_A_PLAYER_TWO_NAME = "Player A2";

    private static final Integer TEAM_A_GOAL_TIME = 123;



    private static final String TEAM_B_NAME = "ToniTeamB";

    private static final String TEAM_B_CAPTAIN_NAME = "CapB";
    private static final String TEAM_B_CAPTAIN_PHONE = "CapB@gmail.com";
    private static final String TEAM_B_CAPTAIN_EMAIL = "39666666666";

    private static final String TEAM_B_PLAYER_ONE_NAME = "Player B1";
    private static final String TEAM_B_PLAYER_TWO_NAME = "Player B2";

    private static final Integer TEAM_B_GOAL_TIME = 136;
    private static final Integer TEAM_B_GOAL_TWO_TIME = 176;

    private static final Integer TOUR_NUMBER = 1;


    @Autowired
	private TestEntityManager entityManager;

	@Autowired
	private GameRepository gameRepository;

	@Autowired
    private GoalRepository goalRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TourRepository tourRepository;


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
        teamAPlayer1.setPlayerName(TEAM_A_PLAYER_ONE_NAME);
        teamAPlayer1.setTeam(teamA);

        Player teamAplayer2 = new Player();
        teamAplayer2.setPlayerName(TEAM_A_PLAYER_TWO_NAME);
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
        teamBplayer.setPlayerName(TEAM_B_PLAYER_ONE_NAME);
        teamBplayer.setTeam(teamB);

        Player teamBplayer2 = new Player();
        teamBplayer2.setPlayerName(TEAM_B_PLAYER_TWO_NAME);
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

        Tour tour = new Tour();
        tour.setTourNumber(TOUR_NUMBER);
        tour.addGame(game);
        game.setTour(tour);

        entityManager.persist(tour);
        entityManager.flush();

        Optional<Tour> tourFromDbOptional = tourRepository.findById(tour.getId());
        assertThat(tourFromDbOptional.isPresent()).isTrue();

        Tour tourFromDb = tourFromDbOptional.get();
        assertThat(tourFromDb.getTourNumber()).isEqualTo(TOUR_NUMBER);

        assertThat(tourFromDb.getGameList().size()).isEqualTo(1);

        assertThat(tourFromDb.getGameList().get(0).getFirstTeam().getTeamName()).isEqualTo(TEAM_A_NAME);
        assertThat(tourFromDb.getGameList().get(0).getFirstTeam().getCaptain().getCaptainName()).isEqualTo(TEAM_A_CAPTAIN_NAME);

        assertThat(tourFromDb.getGameList().get(0).getSecondTeam().getTeamName()).isEqualTo(TEAM_B_NAME);
        assertThat(tourFromDb.getGameList().get(0).getSecondTeam().getCaptain().getCaptainName()).isEqualTo(TEAM_B_CAPTAIN_NAME);

    }

	@After
	public void cleanUp() {
		gameRepository.deleteAll();
	}
	
}
