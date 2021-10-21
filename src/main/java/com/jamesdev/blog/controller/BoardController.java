package com.jamesdev.blog.controller;

import com.jamesdev.blog.model.Board;
import com.jamesdev.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/")
    public String index(Model model, @PageableDefault (size=3, sort="id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<Board> postPage =boardService.getPosts(pageable);
        model.addAttribute("posts",postPage);
        return "index";
    }


    @GetMapping("/post/{id}")
    public String getPost(Model model,@PathVariable long id){
        Board requiredPost = boardService.findPostById(id);
        model.addAttribute("post",requiredPost);
        return "board/post";
    }

    @GetMapping("/post/{id}/editPost")
    public String editPost(Model model,@PathVariable long id){
        model.addAttribute("post",boardService.findPostById(id));
        return "board/editPost";
    }

    @GetMapping("/user/writePost")
    public String writeBoard(){return "board/writePost";}

}
