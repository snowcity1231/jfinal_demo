package com.demo.model;

import com.demo.base.BaseBlog;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Blog extends BaseBlog<Blog> {
	public static final Blog dao = new Blog();
	
	//在Blog中获取关联的User
	public User getUser() {
		return User.dao.findById(get("user_id"));
	}
}
