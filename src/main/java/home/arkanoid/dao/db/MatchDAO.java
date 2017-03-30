package home.arkanoid.dao.db;


import com.google.inject.Inject;
import home.arkanoid.dao.AbstractDAO;
import home.arkanoid.entities.Match;
import home.arkanoid.entities.Player;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class MatchDAO implements AbstractDAO<Match>{

    @Inject
    private SessionFactory factory;

    @Override
    public void insert(Match data) {
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
    public void update(Match data) {
        Session session = factory.openSession();    //псевдо-подключение игрока
        Transaction transaction = session.beginTransaction();   //набор запросов к базе
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
    public void delete(Match data) {
        Session session = factory.openSession();    //псевдо-подключение игрока
        Transaction transaction = session.beginTransaction();   //набор запросов к базе
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
    public Match findByID(int id) {
        Match match = null;

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            match = session.load(Match.class, id);
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
            transaction.rollback();
        }
        return match;
    }

    @Override
    public List<Match> getAll() {
        Session session = factory.openSession();
        return (List<Match>) session.createCriteria(Match.class).list();
    }
}
