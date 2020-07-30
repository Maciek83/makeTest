package com.gosciminski.testsapp.repisitory;

import java.util.List;

import com.gosciminski.testsapp.model.TestQaSolved;
import com.gosciminski.testsapp.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQaSolvedRepository extends CrudRepository<TestQaSolved,Long>{
    List<TestQaSolved> findByUser(User user);
}