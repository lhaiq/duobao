package com.hengsu.duobao.mall.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.duobao.mall.entity.Goods")
public class GoodsModel{
	
	private Long id;
	private Long categoryId;
	private Long sellerId;
	private String name;
	private String image;
	private Integer num;
	private Integer currentSerial;
	private Integer status;
	private Date addTime;
	private Integer sort;
	private Integer type;
	private Double price;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setCategoryId(Long categoryId){
		this.categoryId = categoryId;
	}
	
	public Long getCategoryId(){
		return this.categoryId;
	}
		
	public void setSellerId(Long sellerId){
		this.sellerId = sellerId;
	}
	
	public Long getSellerId(){
		return this.sellerId;
	}
		
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
		
	public void setImage(String image){
		this.image = image;
	}
	
	public String getImage(){
		return this.image;
	}
		
	public void setNum(Integer num){
		this.num = num;
	}
	
	public Integer getNum(){
		return this.num;
	}
		
	public void setCurrentSerial(Integer currentSerial){
		this.currentSerial = currentSerial;
	}
	
	public Integer getCurrentSerial(){
		return this.currentSerial;
	}
		
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return this.status;
	}
		
	public void setAddTime(Date addTime){
		this.addTime = addTime;
	}
	
	public Date getAddTime(){
		return this.addTime;
	}
		
	public void setSort(Integer sort){
		this.sort = sort;
	}
	
	public Integer getSort(){
		return this.sort;
	}
		
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return this.type;
	}
		
	public void setPrice(Double price){
		this.price = price;
	}
	
	public Double getPrice(){
		return this.price;
	}
		
		
}