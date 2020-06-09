package com.capstone.springboot.domain.comments;

import com.capstone.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comments extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250, nullable=false)
    private String content;

    @Column
    private String author;

    @Column
    private Long pid;

    @Column
    private Long tag;

    @Column
    private Long report;

    @Column
    private Long banned;

    @Builder
    public Comments(String content, String author, Long pid, Long tag, Long banned){
        this.content = content;
        this.author = author;
        this.pid = pid;
        this.tag = tag;
        this.banned = banned;
    }

    public void incrementReport(){
        if(this.report == null)
            this.report = 0l;
        this.report = this.report + 1;
    }
    public void reTag() {
        this.tag = 1l;
        this.report = 1l;
    }
    public void ban(){
        this.banned = 1l;
    }
}