package com.capstone.springboot.web.dto;

import com.capstone.springboot.domain.comments.Comments;
import com.capstone.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class CommentsResponseDto {
    private Long id;
    private Long pid;
    private String content;
    private String author;
    private Long tag;
    private Long report;
    private Long banned;

    public CommentsResponseDto(Comments entity){
        this.id = entity.getId();
        this.pid = entity.getPid();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.tag = entity.getTag();
        this.report = entity.getReport();
        this.banned = entity.getBanned();
    }
}
