package com.telemedicine.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="lifelog_boryung_bp")
public class LifelogBoryungBp {

    @Id
    @Column(name="seq_no")
    private int     seqNo ;

    @Column(name="user_id")
    private String  userId;

    @Column(name="mode_type")
    private String  modeType;

    @Column(name="ms_dt")
    private String  msDt;


    private int     systolic;
    private int     diastolic;
    private int     pulse;

    @Column(name="app_version")
    private String  appVersion;

    private String  firmware;

    @Column(name="reg_dt")
    private String  regDt;
}
