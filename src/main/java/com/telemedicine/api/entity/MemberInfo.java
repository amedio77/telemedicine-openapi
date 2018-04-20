package com.telemedicine.api.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="member_info")
public  class MemberInfo {

	@Id
	@Column(name="user_id")
    private String userId;

    private String 	pass;
	private String 	name;
	private String 	Birth;
    private int 	gender;
	private String 	area;
	private String 	phone;
	private String 	email;
	private String 	height;
	private String 	weight;
	private String 	diss;
	private String 	hospital;
	//private String 	depMedi1;
	//private String 	depMedi2;
	//private String 	depMedi3;

	@Column(name="del_yn")
	private String 	delYn;

	@Column(name="sleep_start_time")
	private String 	sleepStartTime;

	@Column(name="sleep_end_time")
	private String 	sleepEndTime;

	@Column(name="reg_dt")
	private String 	regDt;

	private String 	token;

	@Column(name="role_type")
	private int 	roleType;

}
