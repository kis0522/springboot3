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
        int end;
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
        Integer bn = repository.getNumById(id);
        repository.updateBn(bn);
        repository.deleteById(id);
    }

    @Override
    public Integer selectLastBn() {
        if(repository.count()==0){
            return 0;
        }
        return repository.endBn();
    }

    @Override
    public Integer getSizeOfBoard() {
        long count = repository.count();
        int result = Long.valueOf(count).intValue();
        return result;
    }

}
