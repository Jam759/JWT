package com.example.clubs.member.Service;

import com.example.clubs.member.dto.request.CreateMemberRequest;
import com.example.clubs.member.dto.request.UpdateMemberRequest;
import com.example.clubs.member.dto.response.GetMemberResponse;
import com.example.clubs.member.entity.Member;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    CreateMemberRequest createMember(CreateMemberRequest request);

    GetMemberResponse getMemberById(Long id);
    List<GetMemberResponse> getAllMembers();

    UpdateMemberRequest updateMember(Long id, UpdateMemberRequest request);

    void deleteMember(Long id);


}
