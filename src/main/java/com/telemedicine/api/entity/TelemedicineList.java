package com.telemedicine.api.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="telemedicine_list")
public class TelemedicineList {
    @Id
    @Column(name="seq_no")
    private int seqNo;

    @Column(name = "from_id", updatable = false, insertable = false, nullable = false)
    private String fromId;

    @Column(name="to_id")
    private String toId;

    @Column(name="response_dt")
    private String responseDt;

    @Column(name="reqeust_dt")
    private String reqeustDt;

    @Column(name="reg_dt")
    private String regDt;

    @ManyToOne(targetEntity=MemberInfo.class, fetch=FetchType.LAZY)
    @JoinColumn(name="from_id")
    private MemberInfo memberinfo;

}