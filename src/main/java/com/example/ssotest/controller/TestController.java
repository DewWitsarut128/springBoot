package com.example.ssotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ssotest.entity.TestEntity;
import com.example.ssotest.service.TestService;
import com.example.ssotest.entity.Response;

@RestController
@RequestMapping("/apitest")
public class TestController {
    @Autowired
    private TestService testService;

    @PostMapping("/gentoken")
    public ResponseEntity<?> generateToken(@RequestBody TestEntity testEntity) {
        try {
            testEntity.setRequestDate(null);
    
            if (testEntity.getSystemid() == null || testEntity.getUserid() == null || testEntity.getTokenid() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Response("E000002", "ข้อมูลไม่สมบูรณ์ กรุณาตรวจสอบอีกครั้ง", null));
            }
    
            TestEntity savedUserTest = testService.saveTokenDetails(testEntity);
            return ResponseEntity.ok(new Response("I07000", "ทำรายการเรียบร้อย", savedUserTest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("E000001", "ไม่สามารถบันทึกข้อมูลลงฐานข้อมูลได้", null));
        }
    }    
}
