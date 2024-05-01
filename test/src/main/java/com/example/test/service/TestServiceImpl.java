package com.example.test.service;

import com.example.test.entity.Test;
import com.example.test.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TestServiceImpl implements TestService{
    @Autowired
    TestRepository repository;

    @Override
    public Iterable<Test> selectAll() {
        return repository.findAll();
    }

    Integer count = 0;
    @Override
    public Optional<Test> selectOneById(Integer id) {
        Integer startId;
        Integer endId = repository.endId();
        count++;
        startId = count;
        if(startId > endId){
            count = 1;
            startId = count;
        }
        Boolean findId = repository.existsById(startId);
        while(findId == false){
            startId++;
            count = startId;
            findId = repository.existsById(startId);
        }
        return  repository.findById(startId);
    }

    @Override
    public Optional<Test>selectOneRandomTest () {
        return Optional.empty();
    }

    @Override
    public Boolean checkTest(Integer id, Boolean myAnswer) {
        Boolean check = false;
        Optional<Test> optTest = repository.findById(id);
        if(optTest.isPresent()){
            Test test = optTest.get();
            if(test.getAnswer().equals(myAnswer)){
                check = true;
            }
        }
        return check;
    }

    @Override
    public void insertTest(Test test) {
        repository.save(test);
    }

    @Override
    public void updateTest(Test test) {
        repository.save(test);
    }

    @Override
    public void deleteTestById(Integer id) {
        repository.deleteById(id);
    }
}
