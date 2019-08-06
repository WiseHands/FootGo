package ua.lviv.footgo.util;

import ua.lviv.footgo.entity.Captain;
import ua.lviv.footgo.entity.Game;
import ua.lviv.footgo.entity.Player;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.repository.CaptainRepository;
import ua.lviv.footgo.repository.GameRepository;
import ua.lviv.footgo.repository.PlayerRepository;
import ua.lviv.footgo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeamGenerator {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    CaptainRepository captainRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameRepository gameRepository;

    static final int TEAMS = 10;
    static final int PLAYERS = 10;
    static final int NUMBER_OF_TOURS = 9;


    @PostConstruct
    public void createTeam() {
        List allTeams = (List) teamRepository.findAll();
        if (!allTeams.isEmpty()) {
            return;
        }

        for(int i=0; i<TEAMS; i++) {
            Team team = new Team();
            team.setTeamName("Team" + i);

            Captain captain = createCaptain(team);
            team.setCaptain(captain);

            List<Player> playerList = createPlayerList(team);
            team.setPlayers(playerList);
        }

        generateResults();
    }
    public Captain createCaptain(Team team) {
        Captain captain = new Captain();
        captain.setCaptainName("Captain " + team.getTeamName());
        captain.setTeam(team);
        captain = captainRepository.save(captain);
        return captain;
    }
    public Player createPlayer(Team team) {
        Player player = new Player();
        player.setPlayerName("Player");
        player.setTeam(team);
        player = playerRepository.save(player);
        return player;
    }
    public List<Player> createPlayerList(Team team) {
        List<Player> playerList = new ArrayList<Player>();
        for(int i=0; i<PLAYERS; i++) {
            playerList.add(createPlayer(team));
        }
        return playerList;
    }

    public void generateResults() {
        List<Game> allGames = (List<Game>) gameRepository.findAll();
        System.out.println("generateResults\n\n");
        System.out.println(allGames.isEmpty());
        if (!allGames.isEmpty()) {
            return;
        }

        List<Team> allTeams = (List) teamRepository.findAll();
        int i=0;
        for(; i<NUMBER_OF_TOURS; i++) {
            System.out.println(i);
            switch (Integer.valueOf(i)) {
                case 0:
                    createGame(0, 9, allTeams, i);
                    createGame(1, 8, allTeams, i);
                    createGame(2, 7, allTeams, i);
                    createGame(3, 6, allTeams, i);
                    createGame(4, 5, allTeams, i);
                    break;
                case 1:
                    createGame(9, 5, allTeams, i);
                    createGame(6, 4, allTeams, i);
                    createGame(7, 3, allTeams, i);
                    createGame(8, 2, allTeams, i);
                    createGame(0, 1, allTeams, i);
                    break;

                case 2:
                    createGame(1, 9, allTeams, i);
                    createGame(2, 0, allTeams, i);
                    createGame(3, 8, allTeams, i);
                    createGame(4, 7, allTeams, i);
                    createGame(5, 6, allTeams, i);
                    break;

                case 3:
                    createGame(9, 6, allTeams, i);
                    createGame(7, 5, allTeams, i);
                    createGame(8, 4, allTeams, i);
                    createGame(0, 3, allTeams, i);
                    createGame(1, 2, allTeams, i);
                    break;

                case 4:
                    createGame(2, 9, allTeams, i);
                    createGame(3, 1, allTeams, i);
                    createGame(4, 0, allTeams, i);
                    createGame(5, 8, allTeams, i);
                    createGame(4, 7, allTeams, i);
                    break;

                case 5:
                    createGame(9, 7, allTeams, i);
                    createGame(8, 6, allTeams, i);
                    createGame(0, 5, allTeams, i);
                    createGame(1, 4, allTeams, i);
                    createGame(2, 3, allTeams, i);
                    break;
                case 6:
                    createGame(3, 9, allTeams, i);
                    createGame(4, 2, allTeams, i);
                    createGame(5, 1, allTeams, i);
                    createGame(6, 0, allTeams, i);
                    createGame(7, 8, allTeams, i);
                    break;
                case 7:
                    createGame(9, 8, allTeams, i);
                    createGame(0, 7, allTeams, i);
                    createGame(1, 6, allTeams, i);
                    createGame(2, 5, allTeams, i);
                    createGame(3, 4, allTeams, i);
                    break;
                case 8:
                    createGame(4, 9, allTeams, i);
                    createGame(5, 3, allTeams, i);
                    createGame(6, 2, allTeams, i);
                    createGame(7, 1, allTeams, i);
                    createGame(8, 0, allTeams, i);
                    break;

            }
        }

    }

    public void createGame(int indexHomeTeam, int indexGuestTeam, List<Team> allTeams, int tour) {
        Team atHome = allTeams.get(indexHomeTeam);
        Team guestTeam = allTeams.get(indexGuestTeam);
        Game game = new Game();
        game.setFirstTeam(atHome);
        game.setSecondTeam(guestTeam);
        game.setTour(tour);
        game.setGameTime(Instant.now().toString());
        game.setLocation("FC Sokil");
        atHome = teamRepository.save(atHome);
        guestTeam = teamRepository.save(guestTeam);
        gameRepository.save(game);
    }

}
