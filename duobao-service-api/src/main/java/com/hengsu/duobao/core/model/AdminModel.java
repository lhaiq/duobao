package com.hengsu.duobao.core.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hengsu.duobao.core.entity.Admin")
public class AdminModel{
	
	private Long id;
	private String name;
	private String password;
	private Integer role;
	private String authCode;

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getRole() {
		return role;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
		
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return this.password;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getAuthCode() {
		return authCode;
	}
}