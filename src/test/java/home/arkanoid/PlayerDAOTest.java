package home.arkanoid;


import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import home.arkanoid.dao.AbstractDAO;
import home.arkanoid.dao.db.PlayerDAO;
import home.arkanoid.entities.Player;
import home.arkanoid.modules.ProdModule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PlayerDAOTest {

   @Inject
   private AbstractDAO<Player> dao;

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
        Player player = new Player();
        player.setNick_name("Man");
        dao.insert(player);
        Player plr = dao.findByID(1);
        Assert.assertEquals("Man", plr);
    }
}
