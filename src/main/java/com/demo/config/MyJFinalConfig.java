package com.demo.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;

/**
 * Description: 
 * <p>
 * Copyright: Copyright(C) 2014 公司 Inc. All Rights Reserved.  
 * <p>
 *  Department: zhfzm 平台开发组  
 * <p>
 * @Author xuechen
 * <p>
 * @Version 2.0.0  2016-1-7 下午5:31:38
 */
public class MyJFinalConfig extends JFinalConfig {

	@Override
	public void configRoute(Routes me) {
		me.add(new FrontRoutes());		//前端路由
		me.add(new AdminRoutes());		//后端路由
	}

	@Override
	public void configConstant(Constants me) {}
	
	@Override
	public void configPlugin(Plugins me) {
		//TODO
	}

	@Override
	public void configInterceptor(Interceptors me) {}

	@Override
	public void configHandler(Handlers me) {}

}
