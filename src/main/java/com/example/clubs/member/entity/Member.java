package com.example.clubs.member.entity;

import com.example.clubs.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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


    public void update(String memberUserName, String memberEmail) {
        this.memberUserName = memberUserName;
        this.memberEmail = memberEmail;
    }


    @Builder
    public Member(String memberUserName, String memberEmail, String memberPassword) {
        this.memberUserName = memberUserName;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
    }


}
