package com.hengsu.duobao.core.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;

@MapClass("com.hengsu.duobao.core.model.AdminModuleModel")
public class AdminModuleVO{
	
	private Long id;
	private Long adminId;
	private Long moduleId;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setAdminId(Long adminId){
		this.adminId = adminId;
	}
	
	public Long getAdminId(){
		return this.adminId;
	}
		
	public void setModuleId(Long moduleId){
		this.moduleId = moduleId;
	}
	
	public Long getModuleId(){
		return this.moduleId;
	}
		
		
}