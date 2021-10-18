package com.jamesdev.blog.controller.api;

import com.jamesdev.blog.dto.ResponseDto;
import com.jamesdev.blog.dto.UserDto;
import com.jamesdev.blog.model.User;
import com.jamesdev.blog.service.UserService;
import com.jamesdev.blog.vo.EmailVo;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

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
    private ResponseDto<JSONObject>signUp(@Valid @RequestBody UserDto userDto, Errors errors, Model model){
        model.addAttribute("userDto",userDto); //유효성 검사에 실패했을 때 입력 데이터 유지

        if(errors.hasErrors()){
            Map<String,String> validatorResult = userService.validateHandling(errors);
            JSONObject validatorResultJson=new JSONObject(validatorResult);
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), validatorResultJson);
        }
        //유효성 검사 통과
        User user = User
                .builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .gender(userDto.getGender())
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .accountNonExpired(true)
                .build();
        userService.signUp(user);
        JSONObject emptyJson=new JSONObject();
        return new ResponseDto<>(HttpStatus.OK.value(), emptyJson);
    }
}
