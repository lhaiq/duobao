package com.hengsu.duobao.mall.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hengsu.duobao.mall.entity.Order")
public class OrderModel{
	
	private Long id;
	private String orderId;
	private Long userId;
	private Long applyTime;
	private Long finishTime;
	private Integer status;
	private Integer applyType;
	private Double money;
	private String shopList;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setOrderId(String orderId){
		this.orderId = orderId;
	}
	
	public String getOrderId(){
		return this.orderId;
	}
		
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return this.userId;
	}
		
	public void setApplyTime(Long applyTime){
		this.applyTime = applyTime;
	}
	
	public Long getApplyTime(){
		return this.applyTime;
	}
		
	public void setFinishTime(Long finishTime){
		this.finishTime = finishTime;
	}
	
	public Long getFinishTime(){
		return this.finishTime;
	}
		
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return this.status;
	}
		
	public void setApplyType(Integer applyType){
		this.applyType = applyType;
	}
	
	public Integer getApplyType(){
		return this.applyType;
	}
		
	public void setMoney(Double money){
		this.money = money;
	}
	
	public Double getMoney(){
		return this.money;
	}
		
	public void setShopList(String shopList){
		this.shopList = shopList;
	}
	
	public String getShopList(){
		return this.shopList;
	}
		
		
}