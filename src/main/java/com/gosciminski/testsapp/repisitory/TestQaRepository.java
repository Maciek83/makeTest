package com.gosciminski.testsapp.repisitory;

import com.gosciminski.testsapp.model.TestQa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQaRepository extends CrudRepository<TestQa,Long>{
    
}