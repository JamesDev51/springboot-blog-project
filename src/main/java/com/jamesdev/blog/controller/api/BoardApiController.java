package com.jamesdev.blog.controller.api;

import com.jamesdev.blog.config.auth.PrincipalDetails;
import com.jamesdev.blog.dto.ResponseDto;
import com.jamesdev.blog.model.Board;
import com.jamesdev.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private  final  BoardService boardService;

    @PostMapping("/board/api/writePost")
    public ResponseDto<Integer> writePost(@RequestBody Board board ,@AuthenticationPrincipal PrincipalDetails principalDetails) {
        boardService.writePost(board,principalDetails.getUser());
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

}
