package com.hengsu.duobao.mall.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.duobao.mall.entity.Winner")
public class WinnerModel{
	
	private Long id;
	private Long shopId;
	private Long userId;
	private Long code;
	private Date datetime;
	private Long referCode;
	private Long timeCode;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setShopId(Long shopId){
		this.shopId = shopId;
	}
	
	public Long getShopId(){
		return this.shopId;
	}
		
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return this.userId;
	}
		
	public void setCode(Long code){
		this.code = code;
	}
	
	public Long getCode(){
		return this.code;
	}
		
	public void setDatetime(Date datetime){
		this.datetime = datetime;
	}
	
	public Date getDatetime(){
		return this.datetime;
	}
		
	public void setReferCode(Long referCode){
		this.referCode = referCode;
	}
	
	public Long getReferCode(){
		return this.referCode;
	}
		
	public void setTimeCode(Long timeCode){
		this.timeCode = timeCode;
	}
	
	public Long getTimeCode(){
		return this.timeCode;
	}
		
		
}