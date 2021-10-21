package com.jamesdev.blog.controller;

import com.jamesdev.blog.model.Board;
import com.jamesdev.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
public class TestController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/auth/board")
    public String testCheckPosts(@PageableDefault(size=2,sort="id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<Board> boards= boardService.getPosts(pageable);

        Stream<Board> boardsList= boards.stream().distinct();
        return boardsList.toString();

    }
}
