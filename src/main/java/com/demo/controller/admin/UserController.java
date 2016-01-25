package com.demo.controller.admin;

import java.util.List;

import com.demo.aop.DemoInterceptor;
import com.demo.aop.UserInterceptor;
import com.demo.model.User;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

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
	
	private User userDao = User.userDao;
	
	//可以配置多个拦截器
	@Before({DemoInterceptor.class, UserInterceptor.class})
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
		renderJsp("admin/user.jsp");
	}
	
	/**
	 * 添加一条数据到数据库
	 */
	public void register(){
		String userName = getPara("userName");
		String pwd = getPara("password");
		int age = getParaToInt("age");
		new User().set("userName", userName).set("password", pwd).set("age", age).save();
		setSessionAttr("user", userName);
		setAttr("userName", userName);
		renderJsp("admin/user.jsp");
	}
	
	/**
	 * 删除id为1的用户
	 */
	public void delete(){
		//下面方法也可以
		//User.userDao.deleteById("1");
		userDao.deleteById("1");
		renderText("ok");
	}
	
	/**
	 * 查询id值为2的用户并将userName属性修改为tom
	 */
	public void modify(){
		userDao.findById("2").set("userName", "tom").update();
		renderText("ok");
	}
	
	/**
	 * 查询id值为2的user，且仅仅取name与age两个字段的值
	 * 暂不可用
	 */
	/*public void findOnly(){
		User user = userDao.findById(25, "name, age");
		renderText("id:" + user.getInt("userId") + "|userName" + user.getStr("userName") + "|password:" + user.getStr("password")
				+ "|age:" + user.getInt("age"));
	}*/
	
	//查询所有年龄大于10岁的user
	public void queryList(){
		List<User> userList = userDao.find("select * from user where age > 10");
		renderText(Integer.toString(userList.size()));
	}
	
	//分页查询年龄大于10岁的user，当前页号1，每页10个
	public void queryPage() {
		Page<User> userPage = userDao.paginate(1, 10, "select *", "from user where age > ?", 10);
		List<User> userList = userPage.getList();
		renderText(Integer.toString(userList.size()));
		
	}
	
}
