package com.Sethu.CodeClash.db;

import com.Sethu.CodeClash.models.MatchRequest;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MatchRequestDAO {

    public MatchRequest findById(String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(MatchRequest.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(MatchRequest matchRequest) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(matchRequest);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(MatchRequest matchRequest) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(matchRequest);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(MatchRequest matchRequest) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(matchRequest);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<MatchRequest> findByStatus(String status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MatchRequest> query = session.createQuery("FROM MatchRequest WHERE status = :status", MatchRequest.class);
            query.setParameter("status", status);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
