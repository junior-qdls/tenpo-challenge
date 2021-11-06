package com.challenge.tenpo.service;

import com.challenge.tenpo.model.AuditModel;

import java.util.Map;

public interface AuditService{
    void auditInfo(AuditModel auditModel);
    Map<String, Object> listHistory(int page, int size);

}
