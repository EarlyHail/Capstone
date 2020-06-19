package com.capstone.springboot.domain.reports;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column Long cid;

    @Column(length = 250, nullable = false)
    private String content;

    @Column
    private String words;

    @Column
    private Long permission;

    @Builder
    public Reports(Long cid, String content, String words){
        this.cid = cid;
        this.content = content;
        this.words = words;
    }
    public void changePermission(){
        this.permission = 1l;
    }
}
