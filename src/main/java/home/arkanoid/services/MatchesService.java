package home.arkanoid.services;


import home.arkanoid.entities.Match;

import java.util.Date;

public interface MatchesService {

    int publish(int playerId, Date match_time, int score);  //id самого матча нужен?
    void remove(int playerId); //при удалении игрока - удаляются все его матчи
    Match getInfo(int id);
}
