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

    @Column(length = 250, nullable = false)
    private String content;

    @Column
    private String words;

    @Builder
    public Reports(String content, String words){
        this.content = content;
        this.words = words;
    }
}
