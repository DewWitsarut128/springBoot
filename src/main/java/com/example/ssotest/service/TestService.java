package com.example.ssotest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ssotest.entity.TestEntity;
import com.example.ssotest.repository.TestRepository;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    public TestEntity saveTokenDetails(TestEntity testEntity) {
        return testRepository.save(testEntity);
    }
}

