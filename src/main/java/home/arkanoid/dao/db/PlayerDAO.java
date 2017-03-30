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
        try{
            Session session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(data);
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Player data) {
        try{
            Session session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(data);
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Player data) {
        try{
            Session session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(data);
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Override
    public Player findByID(int id) {
        Player player = null;

        try{
            Session session = factory.openSession();
            Transaction transaction = session.beginTransaction();
            player = session.load(Player.class, id);
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
        }
        return player;
    }

    @Override
    public List<Player> getAll() {
        Session session = factory.openSession();
        return (List<Player>) session.createCriteria(Player.class).list();
    }
}
