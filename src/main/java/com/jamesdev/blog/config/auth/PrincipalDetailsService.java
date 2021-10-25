package com.jamesdev.blog.config.auth;

import com.jamesdev.blog.model.User;
import com.jamesdev.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("email : "+email);
        User principal = userRepository.findByEmail(email)
                .orElseThrow(()->{
                    System.out.println("오류 발생");
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.");
        });

        return new PrincipalDetails(principal);
    }
}
