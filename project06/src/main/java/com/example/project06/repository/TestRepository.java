package com.example.project06.repository;

import com.example.project06.entity.Test;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TestRepository extends CrudRepository<Test,Integer> {
    @Query("select id from test order by id desc limit 1")
    Integer endId();

    //게시판 마지막 번호 가져오기
    @Query("select num from test order by id desc limit 1")//역순으로 정렬
    Integer endBn();

    //파라미터로 받아온 아이디 값의 게시판번호 가져오기
    @Query("select num from test where id = :id")
    Integer getNumById(@Param("id") Integer id);

    //삭제한 게시물의 뒷번호 게시판번호 모두 1씩 감소시키기
    @Modifying
    @Query("update test test set test.num = test.num-1 where test.num > :num")
    void updateBn(@Param("num") Integer num);
}