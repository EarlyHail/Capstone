package com.capstone.springboot.service;

import com.capstone.springboot.domain.reports.ReportsRepository;
import com.capstone.springboot.web.dto.ReportsListResponseDto;
import com.capstone.springboot.web.dto.ReportsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportsService {
    private final ReportsRepository reportsRepository;

    @Transactional
    public Long save(ReportsSaveRequestDto requestDto){
        return reportsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<ReportsListResponseDto> findAllDesc(){
        return reportsRepository.findAllDesc().stream()
                .map(ReportsListResponseDto::new) //람다식, map(posts -> new PostsListResponseDto(post))와 동일
                .collect(Collectors.toList());
    }
}
