package home.arkanoid.services;


import home.arkanoid.entities.Player;

import java.util.List;

public interface PlayersService {

    int register(String nickname, String email, String password);
    void delete(int playerId);
    Player getInfo(int id);
    List<Integer> searchPlayer(String nick, String email);
    List<Integer> getBestPlayerMatches(int playerId, int maxCount);
}
