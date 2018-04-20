package com.telemedicine.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="lifelog_croise_sleep")
public class LifelogCroiseSleep {

    @Id
    @Column(name="seq_no")
    private int     seqNo;

    @Column(name="user_id")
    private String  userId;

    @Column(name="ms_dt")
    private String  msDt;

    @Column(name="tot_time")
    private int     totTime;

    @Column(name="deep_time")
    private int     deepTime;

    @Column(name="light_time")
    private int     lightTime;

    @Column(name="rec_index")
    private int     recIndex;

    @Column(name="sleep_index")
    private int     sleepIndex;

    @Column(name="cond_index")
    private int     condIndex;

    @Column(name="app_version")
    private String  appVersion;

    @Column(name="firmware")
    private String  firmware;

    @Column(name="reg_dt")
    private String  regDt;


}
