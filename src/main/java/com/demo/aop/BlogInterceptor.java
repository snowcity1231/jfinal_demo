package com.demo.aop;

import com.demo.model.Blog;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * Description: 
 * <p>
 * Copyright: Copyright(C) 2014 公司 Inc. All Rights Reserved.  
 * <p>
 *  Department: zhfzm 平台开发组  
 * <p>
 * @Author xuechen
 * <p>
 * @Version 2.0.0  2016-1-20 下午5:11:26
 */
public class BlogInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		inv.invoke();
		Blog blog = inv.getController().getModel(Blog.class);
		System.out.println("获取blog title:" + blog.getStr("title"));

	}

}
