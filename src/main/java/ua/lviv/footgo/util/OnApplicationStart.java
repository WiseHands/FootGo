package ua.lviv.footgo.util;

import ua.lviv.footgo.entity.*;
import ua.lviv.footgo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class OnApplicationStart {

    @Autowired
    TourRepository tourRepository;

    @Autowired
    ResultsGenerator resultsGenerator;

    @Autowired
    LeagueManagementRepository leagueManagementRepository;


    @PostConstruct
    public void bootstapApp() {
        List<Tour> tourList = (List<Tour>) tourRepository.findAll();
        if(tourList.size() == 0) {
            resultsGenerator._createLeague();
        }
        List<League> leagueList = (List<League>)leagueManagementRepository.findAll();
        if(leagueList.size() == 0) {
            resultsGenerator._createLeagueStatusIfDoesntExist();
        }
    }

}
