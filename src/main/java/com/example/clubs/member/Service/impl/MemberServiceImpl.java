package com.example.clubs.member.Service.impl;

import com.example.clubs.member.Service.MemberService;
import com.example.clubs.member.dto.MemberDtoConverter;
import com.example.clubs.member.dto.request.CreateMemberRequest;
import com.example.clubs.member.dto.request.UpdateMemberRequest;
import com.example.clubs.member.dto.response.GetMemberResponse;
import com.example.clubs.member.entity.Member;
import com.example.clubs.member.exception.UserNotFoundException;
import com.example.clubs.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberDtoConverter dtoConverter;
    private final MemberRepository memberRepository;

    @Override
    public CreateMemberRequest createMember(CreateMemberRequest request) {

        Member member = dtoConverter.CreateMemberRequestToEntity(request);
        memberRepository.save(member);

        return request;
    }

    @Override
    @Transactional(readOnly = true)
    public GetMemberResponse getMemberById(Long id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("해당 ID의 사용자를 찾을 수 없습니다: " + id));

        return dtoConverter.EntityToGetMemberResponse(member);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetMemberResponse> getAllMembers() {

        return memberRepository.findAll()
                .stream()
                .map(dtoConverter::EntityToGetMemberResponse)
                .toList();

    }

    @Override
    @Transactional
    public UpdateMemberRequest updateMember(Long id, UpdateMemberRequest request) {

        Member updatedMember = memberRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("해당 ID의 사용자를 찾을 수 없습니다: " + id));
        dtoConverter.UpdateMemberRequestToEntity(request,updatedMember);
        memberRepository.save(updatedMember);

        return  request;
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
