package com.telemedicine.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="lifelog_ribbon_bs")
public class LifelogRibbonBs {

    @Id
    @Column(name="seq_no")
    public int      seqNo;

    @Column(name="user_id")
    private String  userId;

    @Column(name="ms_dt")
    private String  msDt;

    @Column(name="bs_value")
    public int      bsValue;

    @Column(name="ms_div")
    public String   msDiv;

    @Column(name="ms_time")
    public String   msTime;

    @Column(name="mode_type")
    public String   modeType;

    @Column(name="app_version")
    public String   appVersion;

    @Column(name="firmware")
    public String   firmware;

    @Column(name="reg_dt")
    private String  regDt;

}
