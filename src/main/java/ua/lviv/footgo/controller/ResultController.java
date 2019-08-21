
package ua.lviv.footgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.footgo.entity.Captain;
import ua.lviv.footgo.entity.Game;
import ua.lviv.footgo.entity.Player;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.jsonmapper.FootballPlayer;
import ua.lviv.footgo.jsonmapper.TeamCreationRequestJsonBody;
import ua.lviv.footgo.jsonmapper.TeamResults;
import ua.lviv.footgo.repository.CaptainRepository;
import ua.lviv.footgo.repository.GameRepository;
import ua.lviv.footgo.repository.PlayerRepository;
import ua.lviv.footgo.repository.TeamRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/games")
public class ResultController {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TeamRepository teamRepository;




    @RequestMapping(value = "/table", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public List<TeamResults> buildResultsTable() {


        List<Game> gameList = (List<Game>) gameRepository.findAll();

        List<Team> teamList = (List<Team>) teamRepository.findAll();

        List<TeamResults> teamResultsList = new ArrayList<>();
        for(Team team: teamList) {
            TeamResults teamResults = new TeamResults();
            teamResults.setTeam(team);

            for(Game game: gameList) {
                _calculateResultForTeamInGame(game, team, teamResults);
            }

            teamResultsList.add(teamResults);
        }

        System.out.println(teamResultsList);
        return teamResultsList;
    }

    private void _calculateResultForTeamInGame(Game game, Team team, TeamResults teamResults) {
        int numberOfTeamAGoals = game.getTeamAGoals().size();
        int numberOfTeamBGoals =  game.getTeamBGoals().size();
        if(game.getFirstTeam() == team) {
            if(game.hasTeamAWin()) {
                teamResults.addWin(numberOfTeamAGoals, numberOfTeamBGoals);
            } else if (game.isADraw()) {
                teamResults.addDraw(numberOfTeamAGoals, numberOfTeamBGoals);
            } else if (game.hasTeamBWin()) {
                teamResults.addLoss(numberOfTeamAGoals, numberOfTeamBGoals);
            }

        } else if (game.getSecondTeam() == team) {
            if(game.hasTeamAWin()) {
                teamResults.addWin(numberOfTeamBGoals, numberOfTeamAGoals);
            } else if (game.isADraw()) {
                teamResults.addDraw(numberOfTeamBGoals, numberOfTeamAGoals);
            } else if (game.hasTeamBWin()) {
                teamResults.addLoss(numberOfTeamBGoals, numberOfTeamAGoals);
            }
        }
    }
}
