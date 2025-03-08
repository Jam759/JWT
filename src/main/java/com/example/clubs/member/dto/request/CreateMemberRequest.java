package com.example.clubs.member.dto.request;

import com.example.clubs.member.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class CreateMemberRequest {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private String memberUserName;
    private String memberEmail;
    private String memberPassword;

    public Member toEntity(){
        return Member.builder()
                .memberEmail(this.memberEmail)
                .memberUserName(this.memberUserName)
                .memberPassword(passwordEncoder.encode(this.memberPassword))
                .build();
    }

}
