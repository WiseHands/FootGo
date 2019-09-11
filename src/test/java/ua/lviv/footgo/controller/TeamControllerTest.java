package ua.lviv.footgo.controller;

import ua.lviv.footgo.jsonmapper.FootballCaptain;
import ua.lviv.footgo.jsonmapper.FootballPlayer;
import ua.lviv.footgo.jsonmapper.TeamCreationRequestJsonBody;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class TeamControllerTest extends AbstractControllerTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createTeamTest() throws Exception {
        String uri = "/team/signup";

        TeamCreationRequestJsonBody teamJsonBody = new TeamCreationRequestJsonBody();
        teamJsonBody.setTeamName("ToniTeams");
        FootballCaptain footballCaptain = new FootballCaptain("Toni", "+380", "@mail");
        teamJsonBody.setCaptain(footballCaptain);

        ArrayList<FootballPlayer> playerList = new ArrayList<>();
        FootballPlayer playerOne = new FootballPlayer("Red", "RedBool");
        playerList.add(playerOne);
        FootballPlayer playerTwo = new FootballPlayer("Blue", "BlueSky");
        playerList.add(playerTwo);

        teamJsonBody.setPlayerList(playerList);

        String inputJson = super.mapToJson(teamJsonBody);
        System.out.println("inputJson " + inputJson);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Team is created successfully");
    }

}
