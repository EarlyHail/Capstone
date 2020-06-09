package com.capstone.springboot.web.dto;

import com.capstone.springboot.domain.comments.Comments;
import com.capstone.springboot.domain.posts.Posts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class CommentsListResponseDto {
    private Long id;
    private Long pid;
    private String content;
    private String author;
    private String modifiedDate;
    private Long tag;
    private Long report;

    private String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }

    public CommentsListResponseDto(Comments entity){
        this.id = entity.getId();
        this.pid = entity.getPid();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.modifiedDate = toStringDateTime(entity.getModifiedDate());
        this.tag = entity.getTag();
        this.report = entity.getReport();
    }
}
