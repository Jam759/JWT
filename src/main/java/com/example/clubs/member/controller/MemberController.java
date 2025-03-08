package com.example.clubs.member.controller;

import com.example.clubs.member.Service.MemberService;
import com.example.clubs.member.dto.request.CreateMemberRequest;
import com.example.clubs.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping()
    public Member createMember(@RequestBody CreateMemberRequest request){
        return memberService.createMember(request);
    }

}
