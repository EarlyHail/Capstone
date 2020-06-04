package com.capstone.springboot.web.dto;

import com.capstone.springboot.domain.reports.Reports;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportsSaveRequestDto {
    private String content;
    private String words;

    @Builder
    public ReportsSaveRequestDto(String content, String words){
        this.content = content;
        this.words = words;
    }

    public Reports toEntity(){
        return Reports.builder()
                .content(content)
                .words(words)
                .build();
    }
}
