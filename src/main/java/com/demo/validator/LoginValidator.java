package com.demo.validator;

import com.demo.model.User;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * Description: 
 * <p>
 * Copyright: Copyright(C) 2014 公司 Inc. All Rights Reserved.  
 * <p>
 *  Department: zhfzm 平台开发组  
 * <p>
 * @Author xuechen
 * <p>
 * @Version 2.0.0  2016-2-19 下午4:09:39
 */
public class LoginValidator extends Validator {
	
	/**
	 * @param c
	 */
	@Override
	protected void validate(Controller c) {
		System.out.println("这是登录校验器：");
		//调用validateXxx(...)系列方法进行后端校验
		//nameMsg、pwdMsg在页面中通过el表达式显示
		validateRequiredString("userName", "nameMsg", "请输入用户名");
		validateRequiredString("password", "pwdMsg", "请输入密码");

	}

	/**
	 * 在校验失败时才会调用
	 * @param c
	 */
	@Override
	protected void handleError(Controller c) {
		//调用keepPara(...)方法将提交的值传回页面以保持原先输入的值
		c.keepPara("userName");
		//传过来的是model对象，可以使用keepModel()方法保持住用户输入过的数据
		//c.keepModel(User.class);
		c.renderJsp("admin/login.jsp");

	}

}
