package com.example.project06.service;

import com.example.project06.entity.Test;
import com.example.project06.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class TestServiceImpl implements TestService{
    @Autowired
    TestRepository repository;

    @Override
    public Iterable<Test> selectAll() {
        ArrayList<Test> reverseBoard = new ArrayList();
        int end = 0;
        if(repository.endId() != null){
            end = repository.endId();
        }else{
            end = 0;
        }
        for(int i = 0; i<end; i++){
            if(repository.existsById(end-i)){
                reverseBoard.add(repository.findById(end-i).get());
            }
        }
        Iterable<Test> iterable = reverseBoard;
        return iterable;
    }
    Integer count = 0;
    @Override
    public Optional<Test> selectOneById(Integer id) {
        return repository.findById(id);
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
