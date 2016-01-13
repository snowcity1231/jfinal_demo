package demo;

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
 * @Version 2.0.0  2016-1-7 下午4:55:09
 */
public class UserController extends Controller {
	@ActionKey("/login")
	public void login() {
		//render("login.html");
		renderText("login");
	}
}
