package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.Cup;
import ua.lviv.footgo.repository.CupManagementRepository;

@RestController
@RequestMapping(path = "/cuplist")
public class CupManagementController {

    @Autowired
    private CupManagementRepository cupManagementRepository;

    @PutMapping(value = "/{cupId}/edit", consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Cup editCup(@PathVariable Long cupId, @RequestParam String cupName, @RequestParam String cupNameEn) {
        Cup cup = cupManagementRepository.findById(cupId).get();
        cup.setName(cupName);
        cup.setNameEn(cupNameEn);
        cupManagementRepository.save(cup);

        return cup;
    }
}
