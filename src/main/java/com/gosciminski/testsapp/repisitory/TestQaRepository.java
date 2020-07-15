package com.gosciminski.testsapp.repisitory;

import java.util.List;

import com.gosciminski.testsapp.model.TestQa;
import com.gosciminski.testsapp.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQaRepository extends CrudRepository<TestQa,Long>{
    List<TestQa> findByUser(User user);
}