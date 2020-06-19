package com.capstone.springboot.web.dto;

import com.capstone.springboot.domain.reports.Reports;

public class ReportsListResponseDto {
    private Long id;
    private Long cid;
    private String content;
    private String words;
    private Long permission;

    public ReportsListResponseDto(Reports entity){
        this.content = entity.getContent();
        this.words = entity.getWords();
        this.id = entity.getId();
        this.cid = entity.getCid();
        this.permission = entity.getPermission();
    }
}
