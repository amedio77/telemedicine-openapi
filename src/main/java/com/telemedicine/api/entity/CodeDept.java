package com.telemedicine.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CodeDept {

    @Id
    @Column(name="code_id")
    private String codeId;

    @Column(name="hosp_id")
    private String hospId;

    @Column(name="constraints")
    private String constraint;
}
