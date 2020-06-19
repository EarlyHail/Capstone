package com.capstone.springboot.domain.reports;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportsRepository extends JpaRepository<Reports, Long> {
    List<Reports> findAll();
}
