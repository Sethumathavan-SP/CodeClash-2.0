package com.Sethu.CodeClash.db;

import com.Sethu.CodeClash.models.TestCase;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestCaseDAO {

    private static final Logger logger = LoggerFactory.getLogger(TestCaseDAO.class);

    public TestCase findById(String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(TestCase.class, id);
        } catch (Exception e) {
            logger.error("Error finding TestCase by ID", e);
            return null;
        }
    }

    public List<TestCase> findByQuestionId(String questionId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<TestCase> query = session.createQuery("FROM TestCase WHERE question.id = :questionId", TestCase.class);
            query.setParameter("questionId", questionId);
            return query.list();
        } catch (Exception e) {
            logger.error("Error finding TestCases by Question ID", e);
            return null;
        }
    }

    public void save(TestCase testCase) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(testCase);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Error saving TestCase", e);
        }
    }

    public void update(TestCase testCase) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(testCase);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Error updating TestCase", e);
        }
    }

    public void delete(TestCase testCase) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(testCase);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Error deleting TestCase", e);
        }
    }
}
