
package ua.lviv.footgo.sitemap;

import cz.jiripinkas.jsitemapgenerator.WebPage;
import cz.jiripinkas.jsitemapgenerator.generator.SitemapGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.lviv.footgo.entity.Game;
import ua.lviv.footgo.entity.Player;
import ua.lviv.footgo.entity.Team;
import ua.lviv.footgo.repository.GameRepository;
import ua.lviv.footgo.repository.PlayerRepository;
import ua.lviv.footgo.repository.TeamRepository;

@RestController
public class SitemapController {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @RequestMapping(value = "/sitemap", produces = MediaType.APPLICATION_XML_VALUE)
    public String sitemap() {
        SitemapGenerator sitemap = SitemapGenerator.of("https://footgo-league.com");
        sitemap.addPage(WebPage.builder().maxPriorityRoot().build());

        WebPage webPage = WebPage.builder().name("results").changeFreqDaily().build();
        sitemap.addPage(webPage);

        webPage = WebPage.builder().name("gametable").changeFreqDaily().build();
        sitemap.addPage(webPage);

        webPage = WebPage.builder().name("bombardier").changeFreqDaily().build();
        sitemap.addPage(webPage);

        Iterable<Game> gameList = gameRepository.findAll();
        for (Game game : gameList) {
            if(game.isCompleted()) {
                String name = "game/" + game.getId();
                webPage = WebPage.builder().name(name).changeFreqDaily().build();
                sitemap.addPage(webPage);
            }
        }

        Iterable<Team> teamList = teamRepository.findAll();
        for (Team team : teamList) {
            String name = "team/" + team.getId();
            webPage = WebPage.builder().name(name).changeFreqDaily().build();
            sitemap.addPage(webPage);
        }

        Iterable<Player> playerList = playerRepository.findAll();
        for (Player player : playerList) {
            String name = "player/" + player.getId();
            webPage = WebPage.builder().name(name).changeFreqDaily().build();
            sitemap.addPage(webPage);
        }

        sitemap.pingGoogle();
        return sitemap.toString();

    }

}
