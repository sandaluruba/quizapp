package com.sandalu.quizapp.controller;
import com.sandalu.quizapp.model.question;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.sandalu.quizapp.service.QuestionService;

import java.util.List;


@RestController
@RequestMapping("questions")
public class questionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestion")
    public List<question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public List<question> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);

    }

    @PostMapping("add")
    public String addQuestion(@RequestBody question question){
        return questionService.addQestion(question);

    }

    @DeleteMapping("delete/{id}")
    public String deleteQuestion(@PathVariable Integer id){
        return questionService.deleteById(id);
    }
}
