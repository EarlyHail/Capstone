package com.capstone.springboot.web.dto;

import com.capstone.springboot.domain.posts.Posts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class PostsListResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String modifiedDate;

    private String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }

    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.modifiedDate = toStringDateTime(entity.getModifiedDate());
    }
}
 