package com.telemedicine.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="code_hosp")
public class CodeHosp {

    @Id
    @Column(name="code_id")
    private String codeId;

    @Column(name="contents")
    private String contents;

    @Column(name="contents_en")
    private String contents_en;

    @Column(name="contents_ch")
    private String contents_ch;
}
