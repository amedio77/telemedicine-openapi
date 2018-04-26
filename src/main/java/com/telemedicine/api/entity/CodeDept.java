package com.telemedicine.api.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="code_dept")
@IdClass(HospDeptId.class)
public class CodeDept {

    @Id
    @Column(name="code_id")
    private String codeId;

    @Id
    @Column(name="hosp_id")
    private String hospId;

    @Column(name="contents")
    private String contents;

    @Column(name="contents_en")
    private String contents_en;

    @Column(name="contents_ch")
    private String contents_ch;
}
