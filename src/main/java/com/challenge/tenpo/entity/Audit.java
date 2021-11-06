package com.challenge.tenpo.entity;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "audit")
@Getter
@Setter
public class Audit {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String requestId;

    private String requestBody;

    private String responseBody;

}
