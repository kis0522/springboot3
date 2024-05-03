package com.example.project06.repository;

import com.example.project06.entity.Test;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<Test,Integer> {
    @Query("SELECT id FROM test ORDER BY id DESC LIMIT 1")
    Integer endId();
}