package com.Sethu.CodeClash.Services;

import com.Sethu.CodeClash.db.QuestionsDAO;
import com.Sethu.CodeClash.db.TestCaseDAO;
import com.Sethu.CodeClash.models.Questions;
import com.Sethu.CodeClash.models.TestCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuestionService {

    private final QuestionsDAO questionsDAO;
    private final TestCaseDAO testCaseDAO;

    public QuestionService(QuestionsDAO questionsDAO, TestCaseDAO testCaseDAO) {
        this.questionsDAO = questionsDAO;
        this.testCaseDAO = testCaseDAO;
    }

    public Questions getRandomQuestion() {
        List<Questions> questions = questionsDAO.findAll();
        if (questions == null || questions.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return questions.get(random.nextInt(questions.size()));
    }

    public Questions getQuestionById(String id) {
        return questionsDAO.findById(id);
    }

    public List<Questions> getQuestionsByDifficulty(Questions.Difficulty difficulty) {
        return questionsDAO.findByDifficulty(difficulty);
    }

    public List<TestCase> getTestCasesForQuestion(String questionId) {
        return testCaseDAO.findByQuestionId(questionId);
    }

    public void createQuestion(String title, String description, Questions.Difficulty difficulty) {
        Questions question = new Questions();
        question.setTitle(title);
        question.setDescription(description);
        question.setDifficulty(difficulty);
        question.setCreatedAt(System.currentTimeMillis());
        questionsDAO.save(question);
    }
}

