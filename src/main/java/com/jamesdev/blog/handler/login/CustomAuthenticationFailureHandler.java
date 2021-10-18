package com.jamesdev.blog.handler.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamesdev.blog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    //로그인이  실패했을 때 로직


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        Map<String,String> dataMap= new HashMap<>();
        dataMap.put("url","/auth/login"); //나중에는 원래 있던 곳으로 돌아갈 수 잇게 세션에서  기존 url 받아옴
        dataMap.put("message","아이디 또는 비밀번호가 일치하지 않습니다.");
        ObjectMapper mapper= new ObjectMapper(); //JSON에 담을 매퍼

        ResponseDto<Map<String,String>> responseDto = new ResponseDto<>(HttpStatus.SERVICE_UNAVAILABLE.value(), dataMap);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(mapper.writeValueAsString(responseDto));
        response.getWriter().flush();


    }
}
