package com.hugo83.mybatis_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hugo83.mybatis_demo.domain.Member;
import com.hugo83.mybatis_demo.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public void addMember(@RequestBody Member member) {
        memberService.save(member);
    }

    @GetMapping
    public List<Member> getMembers() {
        return memberService.getAllMembers();
    }
}
