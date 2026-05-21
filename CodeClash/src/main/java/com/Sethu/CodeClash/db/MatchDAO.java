package com.Sethu.CodeClash.db;

import com.Sethu.CodeClash.models.Match;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchDAO {

    public Match findById(String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Match.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(Match match) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(match);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Match match) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(match);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Match match) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(match);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

