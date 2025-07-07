package com.hugo83.mybatis_demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hugo83.mybatis_demo.domain.Member;

@Mapper
public interface MemberMapper {

    void insert(Member member);
    
    List<Member> findAll();
}
