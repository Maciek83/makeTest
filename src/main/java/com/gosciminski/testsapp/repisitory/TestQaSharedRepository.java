package com.gosciminski.testsapp.repisitory;

import java.util.List;

import com.gosciminski.testsapp.model.TestQaShared;
import com.gosciminski.testsapp.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQaSharedRepository extends JpaRepository<TestQaShared,Long>{
    List<TestQaShared> findByUser(User user);
}