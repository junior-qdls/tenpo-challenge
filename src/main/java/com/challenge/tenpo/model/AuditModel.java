package com.challenge.tenpo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditModel {
    private String requestId;
    private String request;
    private String response;
}
