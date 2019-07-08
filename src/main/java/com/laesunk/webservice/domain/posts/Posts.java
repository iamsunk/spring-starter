package com.laesunk.webservice.domain.posts;

import com.laesunk.webservice.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Setter
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}