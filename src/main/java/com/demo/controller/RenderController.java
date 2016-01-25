package com.demo.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.demo.model.Blog;
import com.demo.model.User;
import com.jfinal.core.Controller;
import com.jfinal.render.XmlRender;

/**
 * Description: render系列方法
 * <p>
 * Copyright: Copyright(C) 2014 公司 Inc. All Rights Reserved.  
 * <p>
 *  Department: zhfzm 平台开发组  
 * <p>
 * @Author xuechen
 * <p>
 * @Version 2.0.0  2016-1-20 下午2:38:47
 */
public class RenderController extends Controller {
	
	/**
	 * 一般render方法，默认视图设置为JSP
	 */
	public void index(){
		setAttr("title", "title");
		setAttr("content", "content");
		render("blogInfo.jsp");
	}
	
	/**
	 * 渲染blog.ftl，视图类型为freemarker
	 */
	public void freemarker(){
		setAttr("title", "ftl_title");
		renderFreeMarker("/freemarker/blog.ftl");
	}
	
	/**
	 * 视图类型为Velocity
	 */
	public void velocity() {
		//TODO
	}
	
	/**
	 * 将setAttr()设置的变量转换成json数据并渲染
	 */
	public void json() {
		setAttr("title", "blogTitle");
		setAttr("author", "tom");
		renderJson();
	}
	
	/**
	 * 以users为跟，仅将userList中数据转换成json
	 */
	public void jsonList() {
		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setUserName("tom");
		user1.setAge(11);
		User user2 = new User();
		user2.setUserName("jack");
		user2.setAge(7);
		userList.add(user1);
		userList.add(user2);
		renderJson("users", userList);
	}
	
	/**
	 * 将user对象转换成json对象
	 */
	public void jsonObj() {
		User user = new User();
		user.setUserName("David");
		user.setAge(17);
		renderJson(user);
	}
	
	/**
	 * 直接渲染json字符串
	 */
	public void jsonTxt() {
		renderJson("{\"age\":18}");
	}
	
	/**
	 * 仅将user和blog转换成json，其他通过setAttr()方法设置的属性不转换
	 */
	public void jsonAttr() {
		User user = new User();
		user.setUserName("Bob");
		user.setAge(31);
		Blog blog = new Blog();
		blog.set("title", "mytitle");
		blog.set("content", "mycontent");
		setAttr("user", user);
		setAttr("blog", blog);
		setAttr("click", 1000);
		renderJson(new String[]{"user", "blog"});
	}
	
	/**
	 * 用于文件下载
	 */
	public void file() {
		File file = new File("C:/Users/Administrator/Desktop/jfinal-2.1-manual.pdf");
		renderFile(file);
	}
	
	/**
	 * 渲染纯文本内容
	 */
	public void text() {
		renderText("Hello Jfinal");
	}
	
	/**
	 * 渲染HTML内容
	 */
	public void html() {
		renderHtml("<ul><li>aaa</li><li>bbb</li><li>ccc</li></ul>");
	}
	
	/**
	 * 渲染login.jsp，且状态为404/500
	 */
	public void error() {
		//renderError(404, "login.jsp");
		renderError(500, "login.jsp");
	}
	
	/**
	 * 不向客户端返回数据
	 */
	public void reNull() {
		renderNull();
	}
	
	/**
	 * 使用自定义的XmlRender来渲染
	 */
	public void xmlRender() {
		//TODO
		render(new XmlRender(""));
	}
}
