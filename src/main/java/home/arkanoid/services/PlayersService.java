package home.arkanoid.services;


import home.arkanoid.entities.Player;

public interface PlayersService {

    int register(String nickname, String email, String password);
    void delete(int playerId);
    Player getInfo(int id);
}
