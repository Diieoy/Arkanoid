package home.arkanoid.services.dao;


import com.google.inject.Inject;
import home.arkanoid.dao.AbstractDAO;
import home.arkanoid.entities.Match;
import home.arkanoid.entities.Player;
import home.arkanoid.services.MatchesService;

import java.util.Date;

public class MatchesServiceDAO implements MatchesService{

    @Inject
    private AbstractDAO<Player> playersDAO;

    @Inject
    private AbstractDAO<Match> matchesDAO;


    @Override
    public int publish(int playerId, Date match_time, int score) {

        Player player = playersDAO.findByID(playerId);

        if(player == null){
            return 0;
        } else{
            Match match = new Match();
            match.setMatch_time(match_time);
            match.setScore(score);

            matchesDAO.insert(match);

            return match.getId();
        }
    }

    @Override
    public Match getInfo(int id) {
        return matchesDAO.findByID(id);
    }
}
