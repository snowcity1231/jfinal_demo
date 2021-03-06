package com.demo.config;

import com.demo.controller.AccountController;
import com.demo.controller.BlogController;
import com.demo.controller.IndexController;
import com.demo.controller.MsgController;
import com.demo.controller.RedisController;
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
 * @Version 2.0.0  2016-1-7 下午5:33:05
 */
public class FrontRoutes extends Routes {

	@Override
	public void config() {
		add("/index", IndexController.class, "/jsp");
		add("/blog", BlogController.class, "/jsp");
		add("/account", AccountController.class, "/jsp");
		add("/redis", RedisController.class);
		add("/msg", MsgController.class);
	}

}
