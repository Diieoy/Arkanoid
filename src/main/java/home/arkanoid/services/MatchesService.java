package home.arkanoid.services;


import home.arkanoid.entities.Match;

import java.util.Date;
import java.util.List;

public interface MatchesService {

    int publish(int playerId, Date match_time, int score);
    List<Integer> bestNumMatches (int playerId, int number);
    Match getInfo(int id);
}
