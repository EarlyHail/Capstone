package com.capstone.springboot.domain.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    @Query("SELECT c FROM Comments c ORDER BY c.id DESC")
    List<Comments> findAllDesc();

    List<Comments> findByPid(Long pid);

    @Query("Select c FROM Comments c Where c.report > 0")
    List<Comments> findAllTag();
}
