package com.example.clubs.member.dto.request;

import com.example.clubs.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
public class CreateMemberRequest {

    private String memberUserName;
    private String memberEmail;
    private String memberPassword;



}
