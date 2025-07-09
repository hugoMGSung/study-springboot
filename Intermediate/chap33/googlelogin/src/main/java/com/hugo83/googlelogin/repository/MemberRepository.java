package com.hugo83.googlelogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hugo83.googlelogin.entity.MemberEntity;


@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    MemberEntity findByUsername(String username);

    MemberEntity findByEmail(String email);
}
