package com.capstone.springboot.service;

import com.capstone.springboot.domain.reports.Reports;
import com.capstone.springboot.domain.reports.ReportsRepository;
import com.capstone.springboot.web.dto.ReportsListResponseDto;
import com.capstone.springboot.web.dto.ReportsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
    public List<ReportsListResponseDto> findAll(){
        return reportsRepository.findAll().stream()
                .map(ReportsListResponseDto::new) //람다식, map(posts -> new PostsListResponseDto(post))와 동일
                .collect(Collectors.toList());
    }

    @Transactional
    public Long changePermission(Long id){
        Reports reports = reportsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. id=" + id));
        reports.changePermission();
        String[] keyword = reports.getWords().split(" ");
        if(keyword[0].length() != 0) {
            List<List<String>> csvData = new ArrayList<List<String>>();
            BufferedReader br = null;
            BufferedWriter bw = null;
            try {
                String line = "";
                br = Files.newBufferedReader(Paths.get("C:\\Users\\HojinLee\\IdeaProjects\\Capstone\\src\\main\\java\\com\\capstone\\springboot\\python\\keyword.csv"));
                while ((line = br.readLine()) != null) {
                    List<String> tmpList = new ArrayList<String>();
                    String array[] = line.split(",");
                    tmpList = Arrays.asList(array);
                    csvData.add(tmpList);
                }
                br.close();

                bw = Files.newBufferedWriter(Paths.get("C:\\Users\\HojinLee\\IdeaProjects\\Capstone\\src\\main\\java\\com\\capstone\\springboot\\python\\keyword.csv"));
                int count = 0;
                for (List<String> newLine : csvData) {
                    List<String> list = newLine;
                    for (String str : list) {
                        bw.write(str);
                        bw.write(",");
                        count++;
                    }
                    bw.newLine();
                }
                for (String dom : keyword) {
                    bw.write(String.valueOf(count));
                    bw.write(",");
                    bw.write(dom);
                    bw.newLine();
                    count++;
                }
                bw.flush();
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return id;
    }
}
