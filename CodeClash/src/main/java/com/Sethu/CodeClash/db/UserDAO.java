package com.Sethu.CodeClash.db;

import com.Sethu.CodeClash.models.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public User findByUserName(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);
            return query.uniqueResult();
        } catch (Exception e) {
            logger.error("Error finding user by username", e);
            return null;
        }
    }

    public User findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (Exception e) {
            logger.error("Error finding user by email", e);
            return null;
        }
    }

    public User findById(String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(User.class, id);
        } catch (Exception e) {
            logger.error("Error finding user by ID", e);
            return null;
        }
    }

    public void save(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Error saving user", e);
        }
    }

    public void update(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Error updating user", e);
        }
    }

    public void delete(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Error deleting user", e);
        }
    }
}
