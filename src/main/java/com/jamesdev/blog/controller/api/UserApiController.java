package com.jamesdev.blog.controller.api;

import com.jamesdev.blog.dto.ResponseDto;
import com.jamesdev.blog.model.User;
import com.jamesdev.blog.service.UserService;
import com.jamesdev.blog.vo.EmailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/api/checkEmailUsed")
    private ResponseDto<Integer> checkEmailUsed(@RequestBody EmailVo emailVo){
        User user = userService.findUserByEmail(emailVo.getEmail());
        if (user.equals(new User())){
            //빈 객체면 회원가입 계속 진행
            return new ResponseDto<>(HttpStatus.OK.value(), 1);
        }else{
            //빈 객체가 아니면 기존에 존재하던 회원이니까 로그인 창으로 이동
            return new ResponseDto<>(HttpStatus.OK.value(),2);
        }
    }
    @PostMapping("/auth/api/signUp")
    private ResponseDto<Integer>signUp(@RequestBody User user){
        userService.signUp(user);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }
}
