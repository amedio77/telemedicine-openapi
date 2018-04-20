package com.telemedicine.user.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@Table(name="user_info")
public  class UserInfo {

	UserInfo(){

	}

	@Id
	@Column(name="user_id")
    private String userId;

    private String 	pass;
	private String 	name;
	private String 	email;

	@Column(name="del_yn")
	private String 	delYn;

	@Column(name="reg_dt")
	private String 	regDt;

	private String 	token;

	@Column(name="role_type")
	private String 	roleType;

	private String 	client_id;

	private String 	client_secret;

}
