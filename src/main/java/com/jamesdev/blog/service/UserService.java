package com.jamesdev.blog.service;

import com.jamesdev.blog.dto.UserEditRequestDto;
import com.jamesdev.blog.dto.UserSignUpRequestDto;
import com.jamesdev.blog.model.RoleType;
import com.jamesdev.blog.model.User;
import com.jamesdev.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User findUserByEmail(String email) {
        //TODO: 나중에 코드 리팩토링하기(노란줄)
        User user = userRepository.findByEmail(email).orElseGet(() -> {
            return new User(); //찾아서 없으면 빈 유저 객체를 리턴
        });
        return user;
    }
    @Transactional
    public void signUp(UserSignUpRequestDto userSignUpRequestDto){
        String rawPassword= userSignUpRequestDto.getPassword();
        String encPassword =bCryptPasswordEncoder.encode(rawPassword);
        User newUser = User
                .builder()
                .name(userSignUpRequestDto.getName())
                .email(userSignUpRequestDto.getEmail())
                .password(encPassword)
                .gender(userSignUpRequestDto.getGender())
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .accountNonExpired(true)
                .role(RoleType.USER)
                .build();
        userRepository.save(newUser);
    }
    public Map<String,String> validateHandling(Errors errors){
        Map <String,String> validateResult = new HashMap<>();
        for(FieldError error: errors.getFieldErrors()){ //유효성 검사에 실패한 필드 목록
            String invalidKeyName=String.format("invalid_%s",error.getField()); //유효성 검사에 실패한 필드명
            validateResult.put(invalidKeyName,error.getDefaultMessage());  //유효성 검사에 실패했을 때 출력해주는 메세지
        }
         return validateResult;
    }

    @Transactional
    public boolean editUser(UserEditRequestDto userEditRequestDto){
        if(!verifyUser(userEditRequestDto.getEmail(), userEditRequestDto.getOriginalPassword())){
            System.out.println("일치 안함");
            return false;
        }
        User persistence = userRepository
                .findByEmail(userEditRequestDto.getEmail())
                .orElseThrow(()->{
                    return new UsernameNotFoundException("없는 유저입니다.");
                });
        String rawPassword= userEditRequestDto.getNewPassword();
        String encPassword= bCryptPasswordEncoder.encode(rawPassword);
        persistence.setPassword(encPassword);
        persistence.setPhoneNumber(userEditRequestDto.getPhoneNumber());
        System.out.println("edit user 종료");
        return true;

    }

    public boolean verifyUser(String email,String password){
        User user=userRepository.findByEmail(email).orElseThrow(()->{
            return new UsernameNotFoundException("없는 유저입니다.");
        });
        boolean bool = bCryptPasswordEncoder.matches(password,user.getPassword());
        System.out.println("같나요 ? "+ bool);
        return bool;
    }


}
