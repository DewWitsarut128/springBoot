package com.example.ssotest.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ssotest.entity.TestEntity;

public interface TestRepository extends JpaRepository<TestEntity, LocalDateTime> {

}
