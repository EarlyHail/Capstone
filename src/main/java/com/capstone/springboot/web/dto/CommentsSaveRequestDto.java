package com.capstone.springboot.web.dto;

import com.capstone.springboot.domain.comments.Comments;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentsSaveRequestDto {
    private String content;
    private String author;
    private Long pid;

    @Builder
    public CommentsSaveRequestDto(String content, String author, Long pid) {
        this.content = content;
        this.author = author;
        this.pid = pid;
    }
    public Comments toEntity(){
        return Comments.builder()
                .content(content)
                .author(author)
                .pid(pid)
                .build();
    }
}
