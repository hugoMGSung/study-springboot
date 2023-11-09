package com.hugo83.api01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hugo83.api01.domain.APIUser;

public interface APIUserRepository extends JpaRepository<APIUser, String> {

}
