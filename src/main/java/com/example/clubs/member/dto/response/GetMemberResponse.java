package com.example.clubs.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetMemberResponse {
    private String memberUserName;
    private String memberEmail;
}
