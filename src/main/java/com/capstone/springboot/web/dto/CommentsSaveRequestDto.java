package com.capstone.springboot.web.dto;

import com.capstone.springboot.domain.comments.Comments;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Getter
@NoArgsConstructor
public class CommentsSaveRequestDto {
    private static PythonInterpreter interpreter;
    private String content;
    private String author;
    private Long pid;
    private Long tag;
    private Long report;
    @Builder
    public CommentsSaveRequestDto(String content, String author, Long pid) {
        this.content = content;
        this.author = author;
        this.pid = pid;
    }

    public Comments toEntity(){
        String comment = this.content;
        String result = null;
        try{
            Runtime r = Runtime.getRuntime();
            Process p = r.exec("cmd /c python C:/Users/user/IdeaProjects/Capstone/src/main/java/com/capstone/springboot/test.py "+comment);
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));
            System.out.println("loop starting....");
            while((result = stdInput.readLine()) != null){
                System.out.println("output: "+result);
                this.tag = (long)Integer.parseInt(result);
            }
            while((result = stdError.readLine()) != null) {
                System.out.println("outputError: " + result);
            }
            System.out.println("loop ending......");
        }catch(IOException e){
            e.printStackTrace();
        }
        if(tag == 1)
            report = tag;
        return Comments.builder()
                .content(content)
                .author(author)
                .pid(pid)
                .tag(tag)
                .report(report)
                .build();
    }
}
