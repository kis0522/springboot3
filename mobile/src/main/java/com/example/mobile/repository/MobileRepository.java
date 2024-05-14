package com.example.mobile.repository;

import com.example.mobile.entity.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileRepository extends JpaRepository<Mobile, Long> {
    Mobile findByEmail(String email);
}
