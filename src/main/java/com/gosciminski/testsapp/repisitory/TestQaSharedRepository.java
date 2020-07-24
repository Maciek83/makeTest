package com.gosciminski.testsapp.repisitory;

import com.gosciminski.testsapp.model.TestQaShared;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQaSharedRepository extends JpaRepository<TestQaShared,Long>{
    
}