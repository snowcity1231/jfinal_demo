package com.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.aop.BlogInterceptor;
import com.demo.aop.DemoInterceptor;
import com.demo.model.Blog;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;

/**
 * Description: 
 * <p>
 * Copyright: Copyright(C) 2014 公司 Inc. All Rights Reserved.  
 * <p>
 *  Department: zhfzm 平台开发组  
 * <p>
 * @Author xuechen
 * <p>
 * @Version 2.0.0  2016-1-7 下午5:29:10
 */
public class BlogController extends Controller {
	
	public void index() {
		renderText("blog");
	}
	
	/**
	 * getModel方法，setAttr方法
	 */
	//method级别的拦截器
	@Before(BlogInterceptor.class)
	public void save() {
		
		//文件默认上传至项目 根路径下的 upload 子路径 之下
		//客户端请求为multipart request，必须先调用getFile方法才能使getPara系列方法正常工作
		UploadFile upFile = getFile("img");
		String imgUrl = "";
		if(null != upFile){
			String fileName = upFile.getFileName();
			imgUrl = "upload/" + fileName;
		}
		
		// 页面的modelName正好是Blog类名的首字母小写
		Blog blog = getModel(Blog.class);
		String title = blog.getStr("title");
		String content = blog.getStr("content");
		setAttr("title", title);
		setAttr("content", content);
		setAttr("path", imgUrl);
		renderJsp("blogInfo.jsp");
		
		
		// 如果表单域的名称为 "otherName.title"可加上一个参数来获取
		//blog = getModel(Blog.class, "otherName");
	}
	
}
