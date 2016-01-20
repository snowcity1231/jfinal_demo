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
 * @Version 2.0.0  2016-1-20 下午5:31:14
 */
public class GlobalActionInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		System.out.println("这是全局拦截器	controller:" + inv.getControllerKey() + ",method:" + inv.getMethodName());
		inv.invoke();

	}

}
