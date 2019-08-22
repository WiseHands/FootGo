package ua.lviv.footgo.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.lviv.footgo.jsonmapper.FootballCaptain;
import ua.lviv.footgo.jsonmapper.FootballPlayer;
import ua.lviv.footgo.jsonmapper.TeamCreationRequestJsonBody;
import ua.lviv.footgo.jsonmapper.TeamSignUpRequestJsonBody;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class TeamSignUpControllerTest extends AbstractControllerTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createTeamTest() throws Exception {
        String uri = "/team/signuprequest";

        TeamSignUpRequestJsonBody teamJsonBody = new TeamSignUpRequestJsonBody("Team", "Toni", "+38096", "test@mail");

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
