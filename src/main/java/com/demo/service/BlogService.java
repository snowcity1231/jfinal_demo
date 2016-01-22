package com.demo.service;

import com.demo.aop.Tx;
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
	public void info(int blogId){
		System.out.println(blogId);
	}
	
	public void inject(){
		System.out.println("service inject");
	}
}
