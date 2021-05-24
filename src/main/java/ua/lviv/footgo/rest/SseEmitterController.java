package ua.lviv.footgo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import ua.lviv.footgo.entity.Game;
import ua.lviv.footgo.repository.GameRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class SseEmitterController {

    @Autowired
    GameRepository gameRepository;

    private final ExecutorService nonBlockingService = Executors
            .newCachedThreadPool();

    @GetMapping("/sse")
    public SseEmitter handleSse(@RequestParam Long gameId) {
        SseEmitter emitter = new SseEmitter();

        Game game = gameRepository.findById(gameId).get();

        nonBlockingService.execute(() -> {
            try {

                emitter.send(SseEmitter.event()
                        .data(game.formatGoalsForTeamA())
                        .name("teamAGoals")
                );
                emitter.send(SseEmitter.event()
                        .data(game.formatGoalsForTeamB())
                        .name("teamBGoals")
                );
                emitter.send(SseEmitter.event()
                        .data(game.formatPenaltyGoalsForTeamA())
                        .name("teamAPenalty")
                );
                emitter.send(SseEmitter.event()
                        .data(game.formatPenaltyGoalsForTeamB())
                        .name("teamBPenalty")
                );
                emitter.send(SseEmitter.event()
                        .data(game.getTeamAStats().getStrikes())
                        .name("teamAStrikes")
                );
                emitter.send(SseEmitter.event()
                        .data(game.getTeamBStats().getStrikes())
                        .name("teamBStrikes")
                );
                emitter.send(SseEmitter.event()
                        .data(game.getTeamAStats().getHitsTheTarget())
                        .name("teamAHitsTheTarget")
                );
                emitter.send(SseEmitter.event()
                        .data(game.getTeamBStats().getHitsTheTarget())
                        .name("teamBHitsTheTarget")
                );
                emitter.send(SseEmitter.event()
                        .data(game.getTeamAStats().getStrikesPastTheGate())
                        .name("teamAStrikesPastTheGate")
                );
                emitter.send(SseEmitter.event()
                        .data(game.getTeamBStats().getStrikesPastTheGate())
                        .name("teamBStrikesPastTheGate")
                );
                emitter.send(SseEmitter.event()
                        .data(game.getTeamAStats().getFreeKicks())
                        .name("teamAFreeKicks")
                );
                emitter.send(SseEmitter.event()
                        .data(game.getTeamBStats().getFreeKicks())
                        .name("teamBFreeKicks")
                );
                emitter.send(SseEmitter.event()
                        .data(game.getTeamAStats().getFouls())
                        .name("teamAFouls")
                );
                emitter.send(SseEmitter.event()
                        .data(game.getTeamBStats().getFouls())
                        .name("teamBFouls")
                );
                emitter.send(SseEmitter.event()
                        .data(game.getTeamAStats().getCornerKicks())
                        .name("teamACornerKicks")
                );
                emitter.send(SseEmitter.event()
                        .data(game.getTeamBStats().getCornerKicks())
                        .name("teamBCornerKicks")
                );

                emitter.complete();
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }
}
