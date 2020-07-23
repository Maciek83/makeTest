package com.gosciminski.testsapp.repisitory;

import com.gosciminski.testsapp.model.TestQaShared;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQaSharedRepository extends CrudRepository<TestQaShared,Long>{
    
}