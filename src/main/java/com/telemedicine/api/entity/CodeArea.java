package com.telemedicine.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="code_area")
public class CodeArea {

    @Id
    @Column(name="code_id")
    private String codeId;

    @Column(name="constraint")
    private String constraint;
}
