package home.arkanoid.dao.db;


import com.google.inject.Inject;
import home.arkanoid.dao.AbstractDAO;
import home.arkanoid.entities.Player;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PlayerDAO implements AbstractDAO<Player> {

    @Inject
    private SessionFactory factory;

    @Override
    public void insert(Player data) {
        Session session = factory.openSession();    //псевдо-подключение игрока
        Transaction transaction = session.beginTransaction();   //набор запросов к базе
        try{
            session.save(data);
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
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
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
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
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
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
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
            transaction.rollback();
        }
        return player;
    }

    @Override
    public List<Player> getAll() {
        Session session = factory.openSession();
        return (List<Player>) session.createCriteria(Player.class).list();
    }
}
