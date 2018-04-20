package com.telemedicine.user.form;


import lombok.Data;

@Data
public  class User {


    private String  userId;
    private String 	pass;
	private String 	name;
	private String 	email;
	private String 	roleType;
}
