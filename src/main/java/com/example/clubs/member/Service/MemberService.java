package com.example.clubs.member.Service;

import com.example.clubs.member.dto.request.CreateMemberRequest;
import com.example.clubs.member.entity.Member;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    Member createMember(CreateMemberRequest member);

    Member getMemberById(Long id);
    List<Member> getAllMembers();

    Member updateMember(Long id, Member member);

    void deleteMember(Long id);


}
