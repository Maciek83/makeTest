package com.gosciminski.testsapp.repisitory;

import java.util.List;

import com.gosciminski.testsapp.model.Question;
import com.gosciminski.testsapp.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question,Long>{
    List<Question> findByUser(User user);
}