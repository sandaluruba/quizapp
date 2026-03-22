package com.sandalu.quizapp.service;

import com.sandalu.quizapp.Dao.QuestionDao;
import com.sandalu.quizapp.Dao.QuizDao;
import com.sandalu.quizapp.model.Quiz;
import com.sandalu.quizapp.model.Response;
import com.sandalu.quizapp.model.question;
import com.sandalu.quizapp.model.questionWrapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<question> questionList = questionDao.findRandomQuestionByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionList);
        quizDao.save(quiz);

        return new ResponseEntity<>("Quiz created successfully", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<List<questionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<question> questionFromDB = quiz.get().getQuestions();
        List<questionWrapper> questionForUser = new ArrayList<>();
        for (question q: questionFromDB){
            questionWrapper qw = new questionWrapper(q.getId(), q.getQuestion_title(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionForUser.add(qw);
        }

        return new ResponseEntity<>(questionForUser, HttpStatus.OK);

    }

    @Transactional
    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {
        Quiz quiz = quizDao.findById(id).get();
        List<question> questionList = quiz.getQuestions();

        int right = 0;
        int i = 0;
        for (Response response1 : response){
            if (response1.getResponse().equals(questionList.get(i).getRight_answer())){
                right ++ ;

            }

            i ++ ;

        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
