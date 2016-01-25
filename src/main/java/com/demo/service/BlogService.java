package com.demo.service;

import com.demo.aop.Tx;
import com.demo.model.Blog;
import com.jfinal.aop.Before;

/**
 * Description: 
 * <p>
 * Copyright: Copyright(C) 2014 公司 Inc. All Rights Reserved.  
 * <p>
 *  Department: zhfzm 平台开发组  
 * <p>
 * @Author xuechen
 * <p>
 * @Version 2.0.0  2016-1-22 下午4:20:23
 */
public class BlogService {
	
	@Before(Tx.class)
	public Blog info(int blogId){
		Blog blog = Blog.dao.findById(blogId);
		return blog;
	}
	
	public void inject(){
		System.out.println("service inject");
	}
}
