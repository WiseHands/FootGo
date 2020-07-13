package ua.lviv.footgo.entity;

import javax.persistence.*;

@Entity
public class GameStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    int strikes;

    @Column
    int hitsTheTarget;

    @Column
    int strikesPastTheGate;

    @Column
    int freeKicks;

    @Column
    int fouls;

    @Column
    int cornerKicks;

}
