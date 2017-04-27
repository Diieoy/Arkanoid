package home.arkanoid.services;


import home.arkanoid.entities.Match;

import java.util.Date;

public interface MatchesService {

    int publish(int playerId, Date match_time, int score);
    void remove(int playerId);
    Match getInfo(int id);
}
