package home.arkanoid.dao;


import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import home.arkanoid.dao.AbstractDAO;
import home.arkanoid.dao.db.PlayerDAO;
import home.arkanoid.entities.Match;
import home.arkanoid.entities.Player;
import home.arkanoid.modules.ProdModule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PlayerDAOTest {

   @Inject
   private AbstractPlayerDAO dao;

   @Inject
   private AbstractDAO<Match> matchesDAO;

    @Before
    public void loadModule(){
        Injector injector = Guice.createInjector(new ProdModule());
        injector.injectMembers(this);
    }

    @Test
    public void createDAO(){
        Assert.assertNotNull(dao);
    }

    @Test
    public void addPlayerDAO(){
        Player player = new Player();
        player.setNick_name("Man");
        player.setPassword("123");
        player.setEmail("man@man.com");

        dao.insert(player);

        List<Player> players = dao.getAll();

        Assert.assertNotEquals(0, players.size());
    }

    @Test
    public void getPlayerDAO(){
       /* Player player = new Player();
        player.setNick_name("Man");
        dao.insert(player);
        Player plr = dao.findByID(1);
        Assert.assertEquals("Man", plr.getNick_name());*/
    }

    @Test
    public void testBestPlayerMatches(){

        Player player = new Player();
        player.setNick_name("Jon");
        player.setPassword("1");
        player.setEmail("1@mail.ru");
        dao.insert(player);

        Match match1 = new Match();
        match1.setPlayer(player);
        match1.setScore(10);
        matchesDAO.insert(match1);

        Match match2 = new Match();
        match2.setPlayer(player);
        match2.setScore(20);
        matchesDAO.insert(match2);

        Match match3 = new Match();
        match3.setPlayer(player);
        match3.setScore(30);
        matchesDAO.insert(match3);

        Match match4 = new Match();
        match4.setPlayer(player);
        match4.setScore(40);
        matchesDAO.insert(match4);

        List<Match> expected = new ArrayList<>();
        expected.add(match1);
        expected.add(match2);
        expected.add(match3);
        expected.add(match4);

        List<Match> actual = dao.getBestPlayerMatches(player, 2);

        Assert.assertArrayEquals(expected.toArray(), actual.toArray());
        //доделатьa
    }
}
