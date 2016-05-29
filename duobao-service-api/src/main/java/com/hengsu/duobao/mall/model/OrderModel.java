package com.hengsu.duobao.mall.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.duobao.mall.entity.Order")
public class OrderModel{
	
	private Long id;
	private String orderId;
	private Long userId;
	private Date applyTime;
	private Date finishTime;
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
		
	public void setApplyTime(Date applyTime){
		this.applyTime = applyTime;
	}
	
	public Date getApplyTime(){
		return this.applyTime;
	}
		
	public void setFinishTime(Date finishTime){
		this.finishTime = finishTime;
	}
	
	public Date getFinishTime(){
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