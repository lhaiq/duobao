package com.hengsu.duobao.core.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

import java.util.Date;

@MapClass("com.hengsu.duobao.core.model.UserModel")
public class UpdateUserVO {
	
	private Long id;
	private String nickname;
	private String mail;
	private String telephone;
	private String avatar;


	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}