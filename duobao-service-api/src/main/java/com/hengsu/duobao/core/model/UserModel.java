package com.hengsu.duobao.core.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.duobao.core.entity.User")
public class UserModel{
	
	private Long id;
	private String nickname;
	private String phone;
	private String password;
	private Date registerTime;
	private String name;
	private String address;
	private String contactName;
	private String idcard;
	private String mail;
	private String telephone;
	private Integer role;
	private Date applyTime;
	private String avatar;
	private Long balance;
	private Long experience;
	private String aid;
	private Integer level;
	private String authCode;

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	
	public String getNickname(){
		return this.nickname;
	}
		
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	public String getPhone(){
		return this.phone;
	}
		
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return this.password;
	}
		
	public void setRegisterTime(Date registerTime){
		this.registerTime = registerTime;
	}
	
	public Date getRegisterTime(){
		return this.registerTime;
	}
		
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
		
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getAddress(){
		return this.address;
	}
		
	public void setContactName(String contactName){
		this.contactName = contactName;
	}
	
	public String getContactName(){
		return this.contactName;
	}
		
	public void setIdcard(String idcard){
		this.idcard = idcard;
	}
	
	public String getIdcard(){
		return this.idcard;
	}
		
	public void setMail(String mail){
		this.mail = mail;
	}
	
	public String getMail(){
		return this.mail;
	}
		
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}
	
	public String getTelephone(){
		return this.telephone;
	}
		
	public void setRole(Integer role){
		this.role = role;
	}
	
	public Integer getRole(){
		return this.role;
	}
		
	public void setApplyTime(Date applyTime){
		this.applyTime = applyTime;
	}
	
	public Date getApplyTime(){
		return this.applyTime;
	}
		
	public void setAvatar(String avatar){
		this.avatar = avatar;
	}
	
	public String getAvatar(){
		return this.avatar;
	}
		
	public void setBalance(Long balance){
		this.balance = balance;
	}
	
	public Long getBalance(){
		return this.balance;
	}
		
	public void setExperience(Long experience){
		this.experience = experience;
	}
	
	public Long getExperience(){
		return this.experience;
	}
		
	public void setAid(String aid){
		this.aid = aid;
	}
	
	public String getAid(){
		return this.aid;
	}
		
	public void setLevel(Integer level){
		this.level = level;
	}
	
	public Integer getLevel(){
		return this.level;
	}
		
		
}