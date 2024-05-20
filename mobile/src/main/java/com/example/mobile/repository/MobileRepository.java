package com.example.mobile.repository;

import com.example.mobile.entity.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MobileRepository extends JpaRepository<Mobile, Long> {
    Mobile findByEmail(String email);

    @Query("SELECT email FROM mobile WHERE email = :email")
    String getEmail(@Param("email") String email);
}
