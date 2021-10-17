package com.jamesdev.blog.service;

import com.jamesdev.blog.model.RoleType;
import com.jamesdev.blog.model.User;
import com.jamesdev.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseGet(() -> {
            return new User(); //찾아서 없으면 빈 유저 객체를 리턴
        });
        return user;
    }
    @Transactional
    public void signUp(User user){
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }
    public Map<String,String> validateHandling(Errors errors){
        Map <String,String> validateResult = new HashMap<>();
        for(FieldError error: errors.getFieldErrors()){ //유효성 검사에 실패한 필드 목록
            String invalidKeyName=String.format("invalid_%s",error.getField()); //유효성 검사에 실패한 필드명
            validateResult.put(invalidKeyName,error.getDefaultMessage());  //유효성 검사에 실패했을 때 출력해주는 메세지
        }
         return validateResult;
    }

}
