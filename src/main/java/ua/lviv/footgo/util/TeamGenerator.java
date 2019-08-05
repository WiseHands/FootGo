package ua.lviv.footgo.util;

import ua.lviv.footgo.entity.Captain;
import ua.lviv.footgo.entity.Player;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.repository.CaptainRepository;
import ua.lviv.footgo.repository.PlayerRepository;
import ua.lviv.footgo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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

    static final int TEAMS = 10;
    static final int PLAYERS = 10;

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
}
