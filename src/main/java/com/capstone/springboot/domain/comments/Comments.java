package com.capstone.springboot.domain.comments;

import com.capstone.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    @Builder
    public Comments(String content, String author, Long pid){
        this.content = content;
        this.author = author;
        this.pid = pid;
    }

    public void incrementTag(){
        if(this.tag == null)
            this.tag = 0l;
        this.tag = this.tag + 1;
    }
}