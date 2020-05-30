package com.capstone.springboot.service;

import com.capstone.springboot.domain.posts.Posts;
import com.capstone.springboot.domain.posts.PostsRepository;
import com.capstone.springboot.web.dto.PostsListResponseDto;
import com.capstone.springboot.web.dto.PostsResponseDto;
import com.capstone.springboot.web.dto.PostsSaveRequestDto;
import com.capstone.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) //조회 기능만 사용 -> 속도 개선
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) //람다식, map(posts -> new PostsListResponseDto(post))와 동일
                .collect(Collectors.toList());
        //postsRepository에서 넘어온 Posts Stream을 map으로 PostsListResponseDto로 변환, List로 반환
    }

    @Transactional
    public void delete(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. id="+id));
        postsRepository.delete(entity);
    }
}
