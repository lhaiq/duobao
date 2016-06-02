package com.hengsu.duobao.core.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hengsu.duobao.core.entity.RecommendRelation")
public class RecommendRelationModel{
	
	private Long id;
	private Long userId;
	private Long recommendId;
	private Long createTime;
		
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
		
	public void setRecommendId(Long recommendId){
		this.recommendId = recommendId;
	}
	
	public Long getRecommendId(){
		return this.recommendId;
	}
		
	public void setCreateTime(Long createTime){
		this.createTime = createTime;
	}
	
	public Long getCreateTime(){
		return this.createTime;
	}
		
		
}