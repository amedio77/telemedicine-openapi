package com.telemedicine.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="lifelog_croise250_activity")
public class LifelogCroise250Activity {

    @Id
    @Column(name="seq_no")
    private int     seqNo;

    @Column(name="user_id")
    private String  userId;

    @Column(name="mode_type")
    private String  modeType;

    @Column(name="ms_dt")
    private String  msDt;

    @Column(name="walk_cnt")
    private int  walkCnt;

    @Column(name="move_distance")
    private float moveDistance;

    private int  calorie;

    @Column(name = "act_time", updatable = false, insertable = false, nullable = true)
    private int     actTime;

    @Column(name="start_time")
    private String  startTime;

    @Column(name="end_time")
    private String  endTime;

    @Column(name="app_version")
    private String  appVersion;

    private String  firmware;

    @Column(name="reg_dt")
    private String  regDt;

}
