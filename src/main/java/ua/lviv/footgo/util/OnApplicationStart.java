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


    @PostConstruct
    public void bootstapApp() {
        List<Tour> tourList = (List<Tour>) tourRepository.findAll();
        if(tourList.size() == 0) {
            resultsGenerator._createLeague();
        }
    }

}
