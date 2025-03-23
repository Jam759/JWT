package com.example.clubs.member.dto.request;

import com.example.clubs.member.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateMemberRequest {

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String memberUserName;


    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String memberEmail;


    private String memberPassword;


    public Member toEntity(){
        return Member.builder()
                .memberUserName(this.memberUserName)
                .memberEmail(this.memberEmail)
                .build();
    }


}
