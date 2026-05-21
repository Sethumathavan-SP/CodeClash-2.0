package com.Sethu.CodeClash.db;

import com.Sethu.CodeClash.models.Questions;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionsDAO {

    public Questions findById(String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Questions.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Questions> findByDifficulty(Questions.Difficulty difficulty) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Questions> query = session.createQuery("FROM Questions WHERE difficulty = :difficulty", Questions.class);
            query.setParameter("difficulty", difficulty);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Questions> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Questions> query = session.createQuery("FROM Questions", Questions.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(Questions question) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(question);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Questions question) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(question);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Questions question) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(question);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
