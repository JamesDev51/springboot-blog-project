package com.jamesdev.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @CreationTimestamp
    private Timestamp joinedDate;

    @Column(columnDefinition = "boolean default true")
    private boolean accountNonExpired;

    @Column(columnDefinition = "boolean default true")
    private boolean accountNonLocked;

    @Column(columnDefinition = "boolean default true")
    private boolean credentialsNonExpired;

    @Column(columnDefinition = "boolean default true")
    private boolean enabled;




}
