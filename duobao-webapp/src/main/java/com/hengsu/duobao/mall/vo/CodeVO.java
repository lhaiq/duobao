package com.hengsu.duobao.mall.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hengsu.duobao.mall.model.CodeModel")
public class CodeVO{
	
	private Long id;
	private Long code;
	private Long shopId;
	private Long userId;
	private Integer status;
	private Long orderId;

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setCode(Long code){
		this.code = code;
	}
	
	public Long getCode(){
		return this.code;
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
		
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return this.status;
	}
		
		
}