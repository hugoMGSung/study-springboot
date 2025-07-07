package com.hugo83.mybatis_demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hugo83.mybatis_demo.domain.Member;
import com.hugo83.mybatis_demo.mapper.MemberMapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private final MemberMapper memberMapper;

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    // public MemberService(MemberMapper memberMapper) {
    //     this.memberMapper = memberMapper;
    // }

    public void save(Member member) {
        // 🔐 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);
        
        try {
            memberMapper.insert(member);
        } catch (Exception e) {
            e.printStackTrace(); // 콘솔에 정확한 위치, 원인 출력
            System.out.println("Exception class: " + e.getClass().getName());
            System.out.println("Exception toString: " + e.toString());
            System.out.println("Exception message: " + e.getMessage());
        }
    }

    public List<Member> getAllMembers() {
        return memberMapper.findAll();
    }
}
