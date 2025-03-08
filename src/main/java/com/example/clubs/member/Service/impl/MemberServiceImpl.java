package com.example.clubs.member.Service.impl;

import com.example.clubs.member.Service.MemberService;
import com.example.clubs.member.dto.request.CreateMemberRequest;
import com.example.clubs.member.entity.Member;
import com.example.clubs.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member createMember(CreateMemberRequest request) {
        Member member = request.toEntity();
        return memberRepository.save(member);
    }

    @Override
    public Member getMemberById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.orElse(null);
    }

    @Override
    public List<Member> getAllMembers() {
        return List.of();
    }

    @Override
    public Member updateMember(Long id, Member member) {
        return null;
    }

    @Override
    public void deleteMember(Long id) {

    }
}
