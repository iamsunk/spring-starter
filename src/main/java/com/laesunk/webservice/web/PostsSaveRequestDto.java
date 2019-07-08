package com.laesunk.webservice.web;

import com.laesunk.webservice.domain.posts.Posts;

public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
