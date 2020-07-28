package com.gosciminski.testsapp.repisitory;

import com.gosciminski.testsapp.model.TestQaSolved;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQaSolvedRepository extends CrudRepository<TestQaSolved,Long>{
    
}