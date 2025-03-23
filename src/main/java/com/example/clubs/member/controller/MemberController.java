package com.example.clubs.member.controller;

import com.example.clubs.member.Service.MemberService;
import com.example.clubs.member.dto.request.CreateMemberRequest;
import com.example.clubs.member.dto.request.UpdateMemberRequest;
import com.example.clubs.member.dto.response.GetMemberResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 🔸 회원 생성
    @PostMapping
    public ResponseEntity<CreateMemberRequest> createMember(@Valid @RequestBody CreateMemberRequest request) {
        return ResponseEntity.ok(memberService.createMember(request));
    }

    // 🔸 전체 회원 조회
    @GetMapping
    public ResponseEntity<List<GetMemberResponse>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    // 🔸 단일 회원 조회
    @GetMapping("/{id}")
    public ResponseEntity<GetMemberResponse> getMember(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    // 🔸 회원 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<UpdateMemberRequest> updateMember(@PathVariable Long id,
                                                            @Valid @RequestBody UpdateMemberRequest request) {
        return ResponseEntity.ok(memberService.updateMember(id, request));
    }

    // 🔸 회원 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
