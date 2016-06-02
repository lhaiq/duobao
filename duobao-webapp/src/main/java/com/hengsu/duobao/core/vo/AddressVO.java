package com.hengsu.duobao.core.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hengsu.duobao.core.model.AddressModel")
public class AddressVO{
	
	private Long id;
	private Long userId;
	private String receiveName;
	private String province;
	private String city;
	private String region;
	private String receiveAddress;
	private String phone;
	private Integer isDefault;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return this.userId;
	}
		
	public void setReceiveName(String receiveName){
		this.receiveName = receiveName;
	}
	
	public String getReceiveName(){
		return this.receiveName;
	}
		
	public void setProvince(String province){
		this.province = province;
	}
	
	public String getProvince(){
		return this.province;
	}
		
	public void setCity(String city){
		this.city = city;
	}
	
	public String getCity(){
		return this.city;
	}
		
	public void setRegion(String region){
		this.region = region;
	}
	
	public String getRegion(){
		return this.region;
	}
		
	public void setReceiveAddress(String receiveAddress){
		this.receiveAddress = receiveAddress;
	}
	
	public String getReceiveAddress(){
		return this.receiveAddress;
	}
		
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	public String getPhone(){
		return this.phone;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getIsDefault() {
		return isDefault;
	}
}