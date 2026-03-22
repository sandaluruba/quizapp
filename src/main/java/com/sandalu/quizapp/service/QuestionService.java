package com.sandalu.quizapp.service;

import com.sandalu.quizapp.Dao.QuestionDao;
import com.sandalu.quizapp.question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public String addQestion(question question) {
        questionDao.save(question);
        return "Question(s) added successfully";

    }

    public List<question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<question> getQuestionByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public String deleteById(Integer id) {
        questionDao.deleteById(id);
        return "Question deleted successfully";
    }

}
