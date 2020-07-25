package com.gosciminski.testsapp.repisitory;

import java.util.List;
import java.util.Optional;

import com.gosciminski.testsapp.model.TestQaShared;
import com.gosciminski.testsapp.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQaSharedRepository extends JpaRepository<TestQaShared,Long>{
    List<TestQaShared> findByUser(User user);

    @Query(value = "SELECT * FROM testqashared t where t.secret = ?1 AND t.id = ?2", nativeQuery = true)
    Optional<TestQaShared> findBySecretAndId(String secret, Long id);

}