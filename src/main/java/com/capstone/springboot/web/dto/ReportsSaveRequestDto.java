package com.capstone.springboot.web.dto;

import com.capstone.springboot.domain.reports.Reports;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportsSaveRequestDto {
    private Long cid;
    private String content;
    private String words;

    @Builder
    public ReportsSaveRequestDto(Long cid, String content, String words){
        this.cid = cid;
        this.content = content;
        this.words = words;
    }

    public Reports toEntity(){
        return Reports.builder()
                .cid(cid)
                .content(content)
                .words(words)
                .build();
    }
}
