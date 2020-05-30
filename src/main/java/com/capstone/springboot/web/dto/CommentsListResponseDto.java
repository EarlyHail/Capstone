package com.capstone.springboot.web.dto;

import com.capstone.springboot.domain.comments.Comments;

import java.time.LocalDateTime;

public class CommentsListResponseDto {
    private Long id;
    private Long pid;
    private String title;
    private String content;
    private String author;
    private LocalDateTime modifiedDate;
    private Long tag;

    public CommentsListResponseDto(Comments entity){
        this.id = entity.getId();
        this.pid = entity.getPid();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
        this.tag = entity.getTag();
    }
}
