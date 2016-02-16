package com.demo.controller.admin;

import com.demo.model.Role;
import com.demo.model.UserRole;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * Description: 
 * <p>
 * Copyright: Copyright(C) 2014 公司 Inc. All Rights Reserved.  
 * <p>
 *  Department: zhfzm 平台开发组  
 * <p>
 * @Author xuechen
 * <p>
 * @Version 2.0.0  2016-2-16 下午2:28:30
 */
public class UserRoleController extends Controller{
	
	private UserRole userRoleDao = new UserRole();
	
	/**
	 * 通过同时指定复合主键查找记录
	 */
	public void index() {
		//添加配置是role_id在前，所以现在前面一个ID是role_id
		UserRole userRole = userRoleDao.findById(1, 2);
		renderText(getRoleName(userRole.getRoleId()));
	}
	
	/**
	 * 通过Db+Record获取复合主键的数据
	 */
	public void findByRecord(){
		try{
			Record userRole = Db.findById("t_user_role", "role_id, user_id", 1, 2);
			int roleId = userRole.getInt("role_id");
			renderText(getRoleName(roleId));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 同时指定复合主键删除记录
	 */
	public void delete() {
		boolean flag = UserRole.dao.deleteById(1, 2);
		renderText(String.valueOf(flag));
	}
	
	/**
	 * 通过Db+Record删除复合主键数据
	 */
	public void deleteByRecord() {
		boolean flag = Db.deleteById("t_user_role", "role_id, user_id", 1, 2);
		renderText(String.valueOf(flag));
	}
	
	/**
	 * 添加一条数据
	 */
	public void add() {
		UserRole userRole = new UserRole();
		userRole.setUserId(2);
		userRole.setRoleId(1);
		userRole.save();
		renderText(userRole.toString());
	}
	
	/**
	 * 通过Db+Record添加数据
	 */
	public void addByRecord() {
		try{
			Record urRecord = new Record();
			urRecord.set("user_id", 2);
			urRecord.set("role_id", 1);
			Db.save("t_user_role", urRecord);
			renderText(urRecord.toJson());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private String getRoleName (int roleId) {
		Role role = Role.dao.findById(roleId);
		return role.getRoleName();
	}
}
