package home.arkanoid.dao;


import home.arkanoid.entities.Match;
import home.arkanoid.entities.Player;

import java.util.List;

public interface AbstractPlayerDAO extends AbstractDAO<Player>{

    List<Player> searchPlayer(String playername);
    List<Match> getBestPlayerMatches(Player player, int maxCount);

}
