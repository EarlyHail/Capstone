package com.capstone.springboot.web.dto;

import com.capstone.springboot.domain.reports.Reports;

public class ReportsListResponseDto {
    private String content;
    private String words;

    public ReportsListResponseDto(Reports entity){
        this.content = entity.getContent();
        this.words = entity.getWords();
    }
}
