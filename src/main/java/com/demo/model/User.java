package com.demo.model;

import java.util.List;

import com.demo.base.BaseUser;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class User extends BaseUser<User> {
	public static final User dao = new User();
	
	public List<Blog> getBlogs() {
		return Blog.dao.find("select * from t_blog where user_id = ?", get("userId"));
	}
}
