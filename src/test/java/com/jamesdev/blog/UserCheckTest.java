package com.jamesdev.blog;

import com.jamesdev.blog.model.User;
import com.jamesdev.blog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserCheckTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findById(){

        User user = userRepository.findById((long) 1).orElseThrow(()->{
            return new IllegalArgumentException("없음");
        });
        System.out.println(user);

    }
}
