package com.jamesdev.blog.service;

import com.jamesdev.blog.model.RoleType;
import com.jamesdev.blog.model.User;
import com.jamesdev.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
