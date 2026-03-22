package com.sandalu.quizapp.Dao;

import com.sandalu.quizapp.question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface QuestionDao extends JpaRepository<question, Integer> {
    List<question> findByCategory(String category);
    void deleteById(Integer id);
}
