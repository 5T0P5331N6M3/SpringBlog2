package com.example.springblog3;

import com.example.springblog3.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Long> {
    Ad findByTitle(String title);
}
