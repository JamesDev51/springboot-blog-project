package com.jamesdev.blog.config.auth;

import com.jamesdev.blog.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class PrincipalDetails implements UserDetails {


    public PrincipalDetails(User user) {
        this.user = user;
    }

    private User user;

    public User getUser(){return this.user;}

    public String getName(){return user.getName();}
    public Long getId(){return user.getId();}
    public String getPhoneNumber(){return user.getPhoneNumber();}

    public String getEmail(){return user.getEmail();}

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collections = new ArrayList<>();
        collections.add(()->{
            return "ROLE_"+user.getRole();
        });
        return collections;
    }
}
