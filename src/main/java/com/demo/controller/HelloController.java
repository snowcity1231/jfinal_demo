package com.demo.controller;

import com.jfinal.core.ActionKey;
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
 * @Version 2.0.0  2016-1-15 下午4:07:43
 */
public class HelloController extends Controller {

	public void index() {
		renderText("Hello Jfinal World.");
	}
	
	/**
	 * 使用actionKey注解，/method直接等于/hello/method
	 */
	@ActionKey("/method")
	public void method() {
		//路径，method/vo-v1 ‘vo-v1’即两个参数，用'-'分开
		String name = getPara(0);
		renderText("Hello " + name);
	}

}
