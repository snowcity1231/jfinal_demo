package com.demo.controller.admin;

import com.demo.aop.DemoInterceptor;
import com.jfinal.aop.Before;
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
 * @Version 2.0.0  2016-1-7 下午5:29:25
 */
//将拦截本类中所以方法
@Before(DemoInterceptor.class)
public class AdminController extends Controller {
	public void index() {
		renderText("admin");
	}
}
