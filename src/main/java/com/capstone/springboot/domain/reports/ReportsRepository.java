package com.capstone.springboot.domain.reports;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportsRepository extends JpaRepository<Reports, Long> {
    @Query("SELECT f FROM Reports f ORDER BY f.id DESC")
    List<Reports> findAllDesc();
}
