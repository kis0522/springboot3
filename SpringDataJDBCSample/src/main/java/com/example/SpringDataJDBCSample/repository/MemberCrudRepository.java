package com.example.SpringDataJDBCSample.repository;

import com.example.SpringDataJDBCSample.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberCrudRepository extends CrudRepository<Member, Integer> {

}
