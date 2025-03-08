package com.example.clubs.member.controller;

import com.example.clubs.member.Service.MemberService;
import com.example.clubs.member.dto.request.CreateMemberRequest;
import com.example.clubs.member.dto.request.UpdateMemberRequest;
import com.example.clubs.member.dto.response.GetMemberResponse;
import com.example.clubs.member.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/member", produces = "application/json")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 🔹 회원 등록 (POST)
    @PostMapping
    public ResponseEntity<CreateMemberRequest> createMember(@RequestBody CreateMemberRequest request) {
        CreateMemberRequest createdMember = memberService.createMember(request);
        return ResponseEntity.ok(createdMember);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetMemberResponse> getMemberById(@PathVariable Long id) {
        GetMemberResponse member = memberService.getMemberById(id);
        return ResponseEntity.ok(member);
    }

    @GetMapping
    public ResponseEntity<List<GetMemberResponse>> getAllMembers() {
        List<GetMemberResponse> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateMemberRequest> updateMember(
            @PathVariable Long id,
            @RequestBody UpdateMemberRequest request) {
        UpdateMemberRequest updatedMember = memberService.updateMember(id, request);
        return ResponseEntity.ok(updatedMember);
    }

    // 🔹 회원 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ 이 컨트롤러 내부에서만 `UserNotFoundException` 처리
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "User Not Found");
        response.put("message", ex.getMessage());
        response.put("status", HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
