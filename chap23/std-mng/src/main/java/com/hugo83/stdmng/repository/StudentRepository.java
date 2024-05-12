package com.hugo83.stdmng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hugo83.stdmng.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}