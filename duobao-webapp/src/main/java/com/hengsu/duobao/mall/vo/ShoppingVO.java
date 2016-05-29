package com.hengsu.duobao.mall.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hengsu.duobao.mall.model.ShoppingModel")
public class ShoppingVO{
	
	private Long id;
	private Long goodsId;
	private Integer serialNumber;
	private Integer num;
	private Integer remainNum;
	private Integer status;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setGoodsId(Long goodsId){
		this.goodsId = goodsId;
	}
	
	public Long getGoodsId(){
		return this.goodsId;
	}
		
	public void setSerialNumber(Integer serialNumber){
		this.serialNumber = serialNumber;
	}
	
	public Integer getSerialNumber(){
		return this.serialNumber;
	}
		
	public void setNum(Integer num){
		this.num = num;
	}
	
	public Integer getNum(){
		return this.num;
	}
		
	public void setRemainNum(Integer remainNum){
		this.remainNum = remainNum;
	}
	
	public Integer getRemainNum(){
		return this.remainNum;
	}
		
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return this.status;
	}
		
		
}