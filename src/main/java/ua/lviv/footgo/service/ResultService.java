package ua.lviv.footgo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.footgo.entity.*;
import ua.lviv.footgo.jsonmapper.TeamResults;
import ua.lviv.footgo.repository.CupManagementRepository;
import ua.lviv.footgo.repository.GameRepository;
import ua.lviv.footgo.repository.LeagueManagementRepository;
import ua.lviv.footgo.repository.TeamRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService {

    private static final Logger logger = LoggerFactory.getLogger(ResultService.class);

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    GameFinder gameFinder;

    @Autowired
    LeagueManagementRepository leagueManagementRepository;

    @Autowired
    CupManagementRepository cupManagementRepository;

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

    public List<TeamResults> getResultsByLeague(boolean forCompletedGamesOnly, Long leagueId) {
        League league = leagueManagementRepository.findById(leagueId).get();
        List<Team> teamList = league.getTeamList();

        List<TeamResults> teamResultsList = new ArrayList<>();
        for(Team team: teamList) {
            TeamResults teamResults = new TeamResults();
            teamResults.setTeam(team);

            List<Game> gameList;
            if(forCompletedGamesOnly) {
                gameList = gameFinder.findAllCompletedGamesForTeamByLeague(leagueId, team);
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

    public List<TeamResults> getResultsByCup(boolean forCompletedGamesOnly, Long cupId) {
        Cup cup = cupManagementRepository.findById(cupId).get();
        List<Team> teamList = cup.getTeamList();

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
        List<Penalty> teamAPenalty = game.getTeamAPenalty().stream().filter(Penalty::getPenaltyGoal).collect(Collectors.toList());
        List<Penalty> teamBPenalty = game.getTeamBPenalty().stream().filter(Penalty::getPenaltyGoal).collect(Collectors.toList());
        int numberOfTeamAGoals = game.getTeamAGoals().size() != game.getTeamBGoals().size() || teamAPenalty.size() == 0 ? game.getTeamAGoals().size() : teamAPenalty.size();
        int numberOfTeamBGoals = game.getTeamAGoals().size() != game.getTeamBGoals().size() || teamBPenalty.size() == 0 ? game.getTeamBGoals().size() : teamBPenalty.size();

        if(!game.getDoNotCountInGameTable() && game.getFirstTeam() == team) {
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

        } else if (!game.getDoNotCountInGameTable() && game.getSecondTeam() == team) {
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
