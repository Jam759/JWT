package com.example.clubs.member.controller;

import com.example.clubs.member.Service.MemberService;
import com.example.clubs.member.dto.response.GetMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class testController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<GetMemberResponse>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

}
