package com.jamesdev.blog.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEditRequestDto {
    private String name;
    private String email;

    @NotBlank(message = "기존 비밀번호가 비었습니다.")
    private String originalPassword;
    private String newPassword;

    @Pattern(regexp = "/^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/",message = "휴대폰 번호 양식이 맞지 않습니다.")
    private String phoneNumber;
}
