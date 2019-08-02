package com.dev2qa.footgo.util;

import com.dev2qa.footgo.entity.Captain;
import com.dev2qa.footgo.entity.Player;
import com.dev2qa.footgo.entity.Team;
import com.dev2qa.footgo.repository.CaptainAccountRepository;
import com.dev2qa.footgo.repository.PlayerAccountRepository;
import com.dev2qa.footgo.repository.TeamAccountRepository;
import org.aspectj.apache.bcel.util.Play;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TeamGenerator {
    @Autowired
    TeamAccountRepository teamAccountRepository;
    @Autowired
    CaptainAccountRepository captainAccountRepository;
    @Autowired
    PlayerAccountRepository playerAccountRepository;

    @PostConstruct
    public void createTeam() {
        for(int i=0; i<5; i++) {
            Team team = new Team();
            Captain captain = createCaptain(team);
            Player player = createPlayer(team);
            team.setCaptain(captain);
            team.setTeamName("Team" + i);
            team = teamAccountRepository.save(team);
        }
    }
    public Captain createCaptain(Team team) {
        Captain captain = new Captain();
        captain.setCaptainName("Captain " + team.getTeamName());
        captain.setTeam(team);
        captain = captainAccountRepository.save(captain);
        return captain;
    }
    public Player createPlayer(Team team) {
        Player player = new Player();
        player.setPlayerName("Player");
        player.setTeam(team);
        player = playerAccountRepository.save(player);
        return player;
    }
}
