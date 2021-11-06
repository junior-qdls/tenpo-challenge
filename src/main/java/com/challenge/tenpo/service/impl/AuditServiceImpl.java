package com.challenge.tenpo.service.impl;

import com.challenge.tenpo.entity.Audit;
import com.challenge.tenpo.model.AuditModel;
import com.challenge.tenpo.repository.AuditRepository;
import com.challenge.tenpo.service.AuditService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

@Service
public class AuditServiceImpl implements AuditService {

    @Resource
    private AuditRepository auditRepository;

    @Override
    public void auditInfo(AuditModel auditModel) {
        Audit audit = auditRepository.findOneByRequestId(auditModel.getRequestId()).orElse(new Audit());
        if(StringUtils.hasText(auditModel.getRequest()))
            audit.setRequestBody(auditModel.getRequest());
        if(StringUtils.hasText(auditModel.getResponse()))
            audit.setResponseBody(auditModel.getResponse());
        audit.setRequestId(auditModel.getRequestId());
        auditRepository.save(audit);
    }

    @Override
    public Map<String, Object> listHistory(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Audit> histories = auditRepository.findAll(paging);
        Map<String, Object> response = new HashMap<>();
        response.put("history", histories);
        response.put("currentPage", histories.getNumber());
        response.put("totalItems", histories.getTotalElements());
        response.put("totalPages", histories.getTotalPages());
        return response;
    }

}
