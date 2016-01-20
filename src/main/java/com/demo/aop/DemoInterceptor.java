package com.demo.aop;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * Description: 
 * <p>
 * Copyright: Copyright(C) 2014 公司 Inc. All Rights Reserved.  
 * <p>
 *  Department: zhfzm 平台开发组  
 * <p>
 * @Author xuechen
 * <p>
 * @Version 2.0.0  2016-1-20 下午4:40:10
 */
public class DemoInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		System.out.println("Before method invoking");
		System.out.println("action key:" + inv.getActionKey());
		System.out.println("controller key:" + inv.getControllerKey());
		System.out.println("method name:" + inv.getMethodName());
		System.out.println("controller key:" + inv.getControllerKey());
		System.out.println("viewpath:" + inv.getViewPath());
		//获取url中的参数，通过getPara()方法获得的
		String para = inv.getController().getPara();
		if(para != null){
			System.out.println("获取参数：" + para);
		}
		
		inv.invoke();
		
		Object[] args = inv.getArgs();
		if(args.length > 0){
			System.out.println("第一个参数：" + args[0]);
		}
		//必须在invoke()方法后才能获取返回值
		String str = inv.getReturnValue();
		System.out.println("return value:" + str);
		System.out.println("After method invoking");

	}

}
