package com.example.clubs.config.security.service;

import com.example.clubs.config.security.entity.User;
import com.example.clubs.member.entity.Member;
import com.example.clubs.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 의존성 주입 (예시로 JPA 사용)
    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member =memberRepository.findBymemberEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user not found : " + email));


        return User.builder()
                .email(member.getMemberEmail())
                .password(member.getMemberPassword())
                .role(User.Role.USER)
                .build();
    }
}
