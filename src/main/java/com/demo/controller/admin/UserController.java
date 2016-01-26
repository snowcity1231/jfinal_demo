package com.demo.controller.admin;

import java.sql.SQLException;
import java.util.List;

import com.demo.aop.DemoInterceptor;
import com.demo.aop.UserInterceptor;
import com.demo.model.User;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.render.Render;

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
	
	private User userDao = User.dao;
	
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
		List<User> list = userDao.find("select * from t_user where user_name = ?", userName);
		if(null != list && list.size() > 0){
			User user = list.get(0);
			if(user.getPassword().equals(pwd)){
				//向session中存储数据
				setSessionAttr("user", userName);
				setAttr("userName", userName);
				renderJsp("admin/user.jsp");
			}else{
				renderText("password error");
			}
		}else{
			renderText("username or password error");
		}
		
		
	}
	
	/**
	 * 添加一条数据到数据库
	 */
	public void register(){
		String userName = getPara("userName");
		String pwd = getPara("password");
		int age = getParaToInt("age");
		new User().set("user_name", userName).set("password", pwd).set("age", age).save();
		setSessionAttr("user", userName);
		setAttr("userName", userName);
		renderJsp("admin/user.jsp");
	}
	
	/**
	 * Db+Record，无需对数据库表进行映射
	 */
	public void registerByRecord(){
		Record user = new Record().set("user_name", "bale").set("age", 13);
		Db.save("t_user_record", user);
		setAttr("userName", "allen");
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
	 * record删除方法
	 */
	public void deleteByRecord(){
		Db.deleteById("t_user_record","user_id", "2");
		renderText("ok");
	}
	
	/**
	 * 查询id值为2的用户并将userName属性修改为tom
	 */
	public void modify(){
		userDao.findById("1").set("userName", "tom").update();
		renderText("ok");
	}
	
	/**
	 * record修改
	 */
	public void modifyByRecord(){
		Record user = Db.findById("t_user_record","user_id", "1");
		user.set("user_name", "Messi");
		Db.update("t_user_record","user_id", user);
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
	
	/**
	 * 
	 */
	/*public void findOnlyByRecord(){
		Record user = Db.findById("t_user_record", "user_id", "1");
		renderText(user.getStr("user_name"));
	}*/
	
	//查询所有年龄大于10岁的user
	public void queryList(){
		List<User> userList = userDao.find("select * from user where age > 10");
		renderText(Integer.toString(userList.size()));
	}
	
	public void queryListByRecord(){
		List<Record> userList = Db.find("select * from t_user_record where age > 14");
		renderText(Integer.toString(userList.size()));
	}
	
	//分页查询年龄大于10岁的user，当前页号1，每页10个
	public void queryPage() {
		Page<User> userPage = userDao.paginate(1, 10, "select *", "from user where age > ?", 10);
		List<User> userList = userPage.getList();
		renderText(Integer.toString(userList.size()));
		
	}
	
	public void queryPageByRecord() {
		Page<Record> userPage = Db.paginate(1, 10, "select *", "from t_user_record where age > ?", 10);
		List<Record> userList = userPage.getList();
		renderText(Integer.toString(userList.size()));
		
	}
	
	//事务处理
	public void testTx() {
		boolean succeed = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				int count = Db.update("update t_user_record set user_name = ? where user_id = ?", "messi", 1);
				int count2 = Db.update("update t_user_record set user_name = ? where user_id = ?", "david", 2);
				return count == 1 && count2 == 1;
			}
		});
		renderText(" " + succeed);
	}
	
}
