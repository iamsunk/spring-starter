package com.laesunk.webservice.controller;

import com.laesunk.webservice.domain.posts.PostsRepository;
import com.laesunk.webservice.web.PostsSaveRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/post")
@Api(tags={"1.post"})
public class WebRestController {

    private PostsRepository postsRepository;
    @ApiOperation( value = "hell test",notes = "테스트")
    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @ApiOperation( value = "글 저장",notes="포스트 저장")
    @PostMapping("/posts")
    public void savePosts(@RequestBody PostsSaveRequestDto dto){
        postsRepository.save(dto.toEntity());
    }
}