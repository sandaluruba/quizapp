package com.sandalu.quizapp.Dao;

import com.sandalu.quizapp.model.question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface QuestionDao extends JpaRepository<question, Integer> {
    List<question> findByCategory(String category);
    void deleteById(Integer id);

    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<question> findRandomQuestionByCategory(String category, int numQ);
}
