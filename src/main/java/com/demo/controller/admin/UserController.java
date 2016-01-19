package com.demo.controller.admin;

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
 * @Version 2.0.0  2016-1-7 下午5:31:11
 */
public class UserController extends Controller {
	public void index() {
		//从seesion中读取数据
		//HttpSession session = getSession();
		//System.out.println(session.getAttribute("user"));
		String user = getSessionAttr("user");
		renderText("welcome to JFinal, " + user + "!");
	}
	
	public void login(){
		String userName = getPara("userName");
		String pwd = getPara("password");
		//向session中存储数据
		setSessionAttr("user", userName);
		setAttr("userName", userName);
		renderJsp("/jsp/admin/user.jsp");
	}
	
}
