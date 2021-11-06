package com.challenge.tenpo.controller;

import com.challenge.tenpo.service.AuditService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/v1/auditing")
public class AuditController {

    @Resource
    private AuditService auditService;

    @GetMapping("/history")
    public ResponseEntity<Map<String, Object>> listHistory(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size){
        return ResponseEntity.ok(auditService.listHistory(page - 1, size));
    }
}
