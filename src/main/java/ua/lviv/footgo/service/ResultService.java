package ua.lviv.footgo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.footgo.entity.Game;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.jsonmapper.TeamResults;
import ua.lviv.footgo.repository.GameRepository;
import ua.lviv.footgo.repository.TeamRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ResultService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    GameFinder gameFinder;

    public List<TeamResults> getResults(boolean forCompletedGamesOnly) {
        List<Team> teamList = (List<Team>) teamRepository.findAll();

        List<TeamResults> teamResultsList = new ArrayList<>();
        for(Team team: teamList) {
            TeamResults teamResults = new TeamResults();
            teamResults.setTeam(team);


            List<Game> gameList;
            if(forCompletedGamesOnly) {
                gameList = gameFinder.findAllCompletedGamesForTeam(team);
            } else {
                gameList = gameFinder.findAllGamesForTeam(team);
            }

            for(Game game: gameList) {
                _calculateResultForTeamInGame(game, team, teamResults);
            }

            teamResultsList.add(teamResults);
        }

        System.out.println(teamResultsList);
        Collections.sort(teamResultsList, new TeamResults.TeamResultsComparator());
        return teamResultsList;
    }

    private void _calculateResultForTeamInGame(Game game, Team team, TeamResults teamResults) {
        int numberOfTeamAGoals = game.getTeamAGoals().size();
        int numberOfTeamBGoals =  game.getTeamBGoals().size();
        if(game.getFirstTeam() == team) {
            if(game.isTeamAHasTechnicalDefeat()) {
                teamResults.addTechnicalLose();
            } else if(game.isTeamBHasTechnicalDefeat()) {
                teamResults.addTechnicalWin();
            } else if(game.hasTeamAWin()) {
                teamResults.addWin(numberOfTeamAGoals, numberOfTeamBGoals);
            } else if (game.isADraw()) {
                teamResults.addDraw(numberOfTeamAGoals, numberOfTeamBGoals);
            } else if (game.hasTeamBWin()) {
                teamResults.addLoss(numberOfTeamAGoals, numberOfTeamBGoals);
            }

        } else if (game.getSecondTeam() == team) {
            if(game.isTeamAHasTechnicalDefeat()) {
                teamResults.addTechnicalWin();
            } else if(game.isTeamBHasTechnicalDefeat()) {
                teamResults.addTechnicalLose();
            } else if(game.hasTeamBWin()) {
                teamResults.addWin(numberOfTeamBGoals, numberOfTeamAGoals);
            } else if (game.isADraw()) {
                teamResults.addDraw(numberOfTeamBGoals, numberOfTeamAGoals);
            } else if (game.hasTeamAWin()) {
                teamResults.addLoss(numberOfTeamBGoals, numberOfTeamAGoals);
            }
        }
    }
}
