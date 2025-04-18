package com.example.clubs.member.mapper;

import com.example.clubs.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MemberMapper {
    Member findById(Long id);
    List<Member> findAll();
    void insert(Member member);
    void update(Member member);
    void delete(Long id);
}
