package com.demo.aop;

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
 * @Version 2.0.0  2016-1-22 下午4:20:46
 */
public class Tx implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		System.out.println("这是事务拦截器");
		inv.invoke();

	}

}
