package com.example.clubs.member.dto;

import com.example.clubs.member.dto.request.CreateMemberRequest;
import com.example.clubs.member.dto.request.UpdateMemberRequest;
import com.example.clubs.member.dto.response.GetMemberResponse;
import com.example.clubs.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberDtoConverter {

    private final PasswordEncoder passwordEncoder;

    // CreateMemberRequest -> Entity
    public Member CreateMemberRequestToEntity(CreateMemberRequest request) {
        return Member.builder()
                .memberEmail(request.getMemberEmail())
                .memberUserName(request.getMemberUserName())
                .memberPassword(passwordEncoder.encode(request.getMemberPassword()))
                .build();
    }

    // UpdateMemberRequest -> Entity
    public void UpdateMemberRequestToEntity(UpdateMemberRequest request,Member updatedMember){
        updatedMember.update(request.getMemberUserName(), request.getMemberEmail());
    }

    // Member → GetMemberResponse (회원 조회 시)
    public GetMemberResponse EntityToGetMemberResponse(Member member) {
        return new GetMemberResponse(member.getMemberUserName(), member.getMemberEmail());
    }




}
