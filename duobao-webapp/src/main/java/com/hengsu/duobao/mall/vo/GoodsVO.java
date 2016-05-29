package com.hengsu.duobao.mall.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.duobao.mall.model.GoodsModel")
public class GoodsVO{

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

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}


	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setCurrentSerial(Integer currentSerial) {
		this.currentSerial = currentSerial;
	}

	public Integer getCurrentSerial() {
		return currentSerial;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}