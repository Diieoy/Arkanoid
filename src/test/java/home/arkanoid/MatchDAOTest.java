package home.arkanoid;


import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import home.arkanoid.dao.AbstractDAO;
import home.arkanoid.entities.Match;
import home.arkanoid.entities.Player;
import home.arkanoid.modules.ProdModule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MatchDAOTest{

    @Inject
    private AbstractDAO<Match> dao_match;

    @Inject
    private AbstractDAO<Player> dao_player;

    @Before
    public void loadModule(){
        Injector injector = Guice.createInjector(new ProdModule());
        injector.injectMembers(this);
    }

    @Test
    public void createDAO(){
        Assert.assertNotNull(dao_match);
    }

    @Test
    public void addPlayerDAO(){
        Player player = new Player();
        dao_player.insert(player);
        Match match = new Match();
        match.setPlayer(player);
        match.setMatch_time(new java.util.Date ());
        match.setScore(100);

        dao_match.insert(match);

        List<Match> matches = dao_match.getAll();

        Assert.assertNotEquals(0, matches.size());
    }
}
