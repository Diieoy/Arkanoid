package home.arkanoid.modules;


import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import home.arkanoid.dao.AbstractDAO;
import home.arkanoid.dao.AbstractPlayerDAO;
import home.arkanoid.dao.db.MatchDAO;
import home.arkanoid.dao.db.PlayerDAO;
import home.arkanoid.entities.Match;
import home.arkanoid.entities.Player;
import home.arkanoid.services.MatchesService;
import home.arkanoid.services.PlayersService;
import home.arkanoid.services.dao.MatchesServiceDAO;
import home.arkanoid.services.dao.PlayersServiceDAO;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.util.Properties;

public class ProdModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(new TypeLiteral<AbstractDAO<Player>>(){}).to(PlayerDAO.class);
        bind(AbstractPlayerDAO.class).to(PlayerDAO.class);
        bind(new TypeLiteral<AbstractDAO<Match>>(){}).to(MatchDAO.class);
        bind(PlayersService.class).to(PlayersServiceDAO.class);
        bind(MatchesService.class).to(MatchesServiceDAO.class);
    }

    @Provides
    @Singleton
    public SessionFactory provideSessionFactory(){

        Properties properties = new Properties();

        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("hibernate.properties"));
        } catch (IOException e){
            e.printStackTrace();
        }

        Configuration cfg = new Configuration().addProperties(properties);
        cfg = cfg.addAnnotatedClass(Player.class)
                .addAnnotatedClass(Match.class);

        ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();

        return cfg.buildSessionFactory(sr);
    }
}
