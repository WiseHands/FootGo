
package ua.lviv.footgo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.TeamSignUpRequest;
import ua.lviv.footgo.jsonmapper.TeamSignUpRequestJsonBody;
import ua.lviv.footgo.repository.TeamSignUpRepository;
import ua.lviv.footgo.util.Mailer;

import java.time.Clock;

@Controller
@RequestMapping(path = "/team")
public class TeamSignUpController {

    @Autowired
    TeamSignUpRepository teamSignUpRepository;

    @Value("${mailer.userName}")
    private String gmailUserName;

    @Value("${mailer.password}")
    private String gmailPassword;

    @Value("${footgo.admin.email}")
    private String footGoAdmin;

    @RequestMapping(value = "/signuprequest", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addTeamViaJson(@RequestBody TeamSignUpRequestJsonBody teamSignUpRequestJsonBody, @RequestHeader(value="User-Agent") String userAgent) {

        TeamSignUpRequest team = new TeamSignUpRequest();
        team.setTeamName(teamSignUpRequestJsonBody.getTeamName());
        team.setCaptainName(teamSignUpRequestJsonBody.getCaptainName());
        team.setCaptainPhone(teamSignUpRequestJsonBody.getCaptainPhone());
        team.setCaptainEmail(teamSignUpRequestJsonBody.getCaptainEmail());
        team.setUtcDateTime(Clock.systemUTC().instant().toString());
        team.setUserAgent(userAgent);
        teamSignUpRepository.save(team);

        String teamName = teamSignUpRequestJsonBody.getTeamName();
        String captainName = teamSignUpRequestJsonBody.getCaptainName();
        String captainEmail = teamSignUpRequestJsonBody.getCaptainEmail();
        String captainPhone = teamSignUpRequestJsonBody.getCaptainPhone();

        String titleMessage = "Заявка на участь. FootGO - аматорський турнір з футболу";
        String message = String.format("Доброго дня капітане %s, ваша заявка надійшла у систему. Невдовзі з вами зв'яжуться організатори", captainName, teamName);


        //for send message check to own Gmail setting https://myaccount.google.com/lesssecureapps pls turn ON for sending message
        Mailer.send(gmailUserName, gmailPassword, captainEmail, titleMessage, message);

        String adminMessage = String.format("Отримано нову заявку на участь в турнірі, команда %s, контактні дані капітана %s %s", teamName, captainName, captainPhone);
        String adminEmailTitle = String.format("Нова заявка на участь FootGO від команди %s", teamName);
        Mailer.send(gmailUserName, gmailPassword, footGoAdmin, adminEmailTitle, adminMessage);
        return new ResponseEntity<>("Team is created successfully", HttpStatus.CREATED);
    }

}
