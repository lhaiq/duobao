package com.hengsu.duobao.core.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hengsu.duobao.core.entity.Module")
public class ModuleModel{
	
	private Long id;
	private String name;
	private String url;
	private Long parentId;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
		
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return this.url;
	}
		
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}
	
	public Long getParentId(){
		return this.parentId;
	}
		
		
}