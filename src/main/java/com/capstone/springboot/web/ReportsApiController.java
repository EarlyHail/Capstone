package com.capstone.springboot.web;

import com.capstone.springboot.service.CommentsService;
import com.capstone.springboot.service.ReportsService;
import com.capstone.springboot.web.dto.ReportsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReportsApiController {
    private final CommentsService commentsService;
    private final ReportsService reportsService;

    @PostMapping("api/v1/reports")
    public Long save(@RequestBody ReportsSaveRequestDto requestDto) {
        return reportsService.save(requestDto);
    }

    @PostMapping("api/v1/reports/accept/{id}")
    public Long accept(@PathVariable Long id){
        return reportsService.changePermission(id);
    }
}

