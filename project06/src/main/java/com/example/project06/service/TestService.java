package com.example.project06.service;

import com.example.project06.entity.Test;

import java.util.Optional;

public interface TestService {
    Iterable<Test> selectAll();
    Optional<Test> selectOneById(Integer id);
    void insertTest(Test test);
    void updateTest(Test test);
    void deleteTestById(Integer id);
    Integer selectLastBn();
    Integer getSizeOfBoard();
}
