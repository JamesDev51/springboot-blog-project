package com.jamesdev.blog.dto;

import com.jamesdev.blog.model.Gender;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class UserDto {
    @NotBlank(message="이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message="이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message="비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="/^[a-zA-Z0-9]{4,12}$/",message ="비밀번호는 4~12자의 영문 대소문자와 숫자로만 입력 가능합니다." )
    private String password;

    @NotBlank(message="성별은 필수 입력 값입니다.")
    private Gender gender;

    private boolean rememberMe;

}
