package com.hugo83.board_back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hugo83.board_back.entity.SiteUser;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
	Optional<SiteUser> findByusername(String username);
}