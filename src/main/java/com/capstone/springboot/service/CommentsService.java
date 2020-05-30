package com.capstone.springboot.service;

import com.capstone.springboot.domain.comments.Comments;
import com.capstone.springboot.domain.comments.CommentsRepository;
import com.capstone.springboot.web.dto.CommentsListResponseDto;
import com.capstone.springboot.web.dto.CommentsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;

    @Transactional
    public Long save(CommentsSaveRequestDto requestDto){
        return commentsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public List<CommentsListResponseDto> findByPid(Long id){
        return commentsRepository.findByPid(id).stream()
                .map(CommentsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CommentsListResponseDto> findAllTag(){
        return commentsRepository.findAllTag().stream()
                .map(CommentsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long report(Long id){
        Comments comment = commentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. id=" + id));;
        comment.incrementTag();
        return id;
    }
}

