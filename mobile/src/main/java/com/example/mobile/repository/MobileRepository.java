package com.example.mobile.repository;

import com.example.mobile.entity.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MobileRepository extends JpaRepository<Mobile, Long> {
    Mobile findByEmail(String email);

    @Query(value="select * from mobile i where i.email like " + "%:email% order by i.email limit 1", nativeQuery = true)
    /*@Query(value="select * from Mobile where email = :email", nativeQuery = true)*/
    Mobile findByEmailByNative(@Param("email") String email);

}