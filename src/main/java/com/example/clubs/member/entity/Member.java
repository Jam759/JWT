package com.example.clubs.member.entity;

import com.example.clubs.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 50, unique = true, nullable = false)
    private String memberUserName;

    @Column(length = 100, unique = true, nullable = false)
    private String memberEmail;

    @Column(length = 100, nullable = false)
    private String memberPassword;

    // 새로운 회원을 생성하는 정적 메서드 (패스워드 암호화 고려)
    public static Member createMember(String username, String email, String password) {
        return Member.builder()
                .memberUserName(username)
                .memberEmail(email)
                .memberPassword(password)  // 실제 구현 시 암호화된 값 사용
                .build();
    }

}
