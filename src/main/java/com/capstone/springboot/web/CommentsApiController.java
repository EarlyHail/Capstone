package com.capstone.springboot.web;

import com.capstone.springboot.service.CommentsService;
import com.capstone.springboot.web.dto.CommentsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentsApiController {
    private final CommentsService commentsService;

    @PostMapping("/api/v1/comments")
    public Long save(@RequestBody CommentsSaveRequestDto requestDto){
        return commentsService.save(requestDto);
    }

    @PutMapping("/api/v1/report/{id}")
    public Long report(@PathVariable Long id){
        commentsService.report(id);
        return id;
    }
    @PostMapping("api/v1/retagging")
    public Long reTagging(){
        return commentsService.reTagging();
    }
}
