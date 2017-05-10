package home.arkanoid.dao.db;


import com.google.inject.Inject;
import home.arkanoid.dao.AbstractPlayerDAO;
import home.arkanoid.entities.Match;
import home.arkanoid.entities.Player;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class PlayerDAO implements AbstractPlayerDAO {

    @Inject
    private SessionFactory factory;

    @Override
    public void insert(Player data) {
        Session session = factory.openSession();    //псевдо-подключение игрока
        Transaction transaction = session.beginTransaction();   //набор запросов к базе
        try{
            session.save(data);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        }
    }

    @Override
    public void update(Player data) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.update(data);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        }
    }

    @Override
    public void delete(Player data) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.delete(data);
            transaction.commit();
        }  catch (Exception e){
            transaction.rollback();
        }
    }

    @Override
    public Player findByID(int id) {
        Player player = null;

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            player = session.load(Player.class, id);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        }
        return player;
    }

    @Override
    public List<Player> getAll() {
        Session session = factory.openSession();
        return (List<Player>) session.createCriteria(Player.class).list();
    }

    @Override
    public List<Player> searchPlayer(String playername) {

        Session session = factory.openSession();

        Criteria criteria = session.createCriteria(Player.class);
        criteria.add(Restrictions.like("playername", playername + "%"));

        return (List<Player>)criteria.list();
    }

    @Override
    public List<Match> getBestPlayerMatches(Player player, int maxCount) {
        
        Session session = factory.openSession();
        Criteria criteria = session.createCriteria(Match.class);
        criteria.add(Restrictions.eq("player", player));
        criteria.addOrder(Order.desc("score"));
        criteria.setMaxResults(maxCount);

        return (List<Match>) criteria.list();
    }
}
