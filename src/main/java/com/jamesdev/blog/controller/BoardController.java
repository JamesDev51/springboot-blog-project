package com.jamesdev.blog.controller;

import com.jamesdev.blog.model.Board;
import com.jamesdev.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String index(Model model, @PageableDefault (size=3, sort="id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<Board> postPage =boardService.getPosts(pageable);
        model.addAttribute("posts",postPage);
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false)String keyword, Model model, @PageableDefault(size=3,sort="id",direction=Sort.Direction.DESC)Pageable pageable) {
        Page<Board> searchedPosts = boardService.searchPosts(pageable,keyword);
        model.addAttribute("posts",searchedPosts);
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
