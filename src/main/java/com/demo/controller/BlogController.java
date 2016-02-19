package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.demo.aop.BlogInterceptor;
import com.demo.aop.Tx;
import com.demo.model.Blog;
import com.demo.model.User;
import com.demo.service.BlogService;
import com.jfinal.aop.Before;
import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.CacheName;
import com.jfinal.plugin.ehcache.EvictInterceptor;
import com.jfinal.plugin.ehcache.IDataLoader;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
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
		blog.save();
		String title = blog.getStr("title");
		String content = blog.getStr("content");
		setAttr("title", title);
		setAttr("content", content);
		setAttr("path", imgUrl);
		renderJsp("blogInfo.jsp");
		
		
		// 如果表单域的名称为 "otherName.title"可加上一个参数来获取
		//blog = getModel(Blog.class, "otherName");
	}
	
	/**
	 * 业务层AOP
	 */
	public void info(){
		int blogId = getParaToInt(0);
		//使用enhance方法对业务层进行增强，使其具有AOP能力
		BlogService service = enhance(BlogService.class);
		//调用info方法时会触发拦截器
		Blog blog = service.info(blogId);
		renderText(blog.getTitle());
	}

	/**
	 * 从缓存中获取博客列表
	 */
	public void listFromCache(){
		List<Blog> blogList = Blog.dao.findByCache("cacheBlogList", "blogList", "select * from t_blog");
		renderText(Integer.toString(blogList.size()));
	}
	
	/**
	 * 通过Db+Record+cache获取数据
	 */
	public void dbByCache() {
		Page<Record> pages = Db.paginateByCache("cacheBlogList", "blogList_1_2", 1, 2, "select *", "from t_blog");
		List<Record> list = pages.getList();
		renderText("" + list.size());
	}
	
	/**
	 * 根据key获取缓存数据
	 */
	public void getFromCache() {
		try{
			String cacheName = "cacheBlogList";
			String key = "blogList";
			List<Blog> list = CacheKit.get(cacheName, key);
			renderText(list.size() + "");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 缓存中移除博客列表
	 */
	public void removeListFromCache() {
		CacheKit.remove("cacheBlogList", "blogList");
		renderText("ok");
	}
	
	/**
	 * CacheInterceptor可以将action所需数据全部缓存起来，下次请求到来时如果cache存在则直接使用数据并render,而不会去调用action
	 * 配合CacheName注解可以取代使用默认的actionKey作为cacheName
	 * 需要再ehcache.xml中配置名为blogList的cache
	 */
	@Before(CacheInterceptor.class)
	@CacheName("cacheBlogList")
	public void listCache() {
		List<Blog> blogList = Blog.dao.find("select * from t_blog");
		System.out.println(blogList.size());
		setAttr("blogList", blogList);
		setAttr("title", "列表");
		renderFreeMarker("/freemarker/blog.ftl");
	}
	
	/**
	 * EvictInterceptor可以根据CacheName注解自动清除缓存
	 */
	@Before(EvictInterceptor.class)
	@CacheName("cacheBlogList")
	public void update() {
		boolean b = getModel(Blog.class).update();
		renderText(String.valueOf(b));
	}
	
	/**
	 * 使用CacheKit操作缓存
	 */
	public void listByCacheKit() {
		List<Blog> blogList = CacheKit.get("cacheBlogList", "blogList");
		if(blogList == null) {
			blogList = Blog.dao.find("select * from t_blog");
			CacheKit.put("cacheBlogList", "blogList", blogList);
		}
		setAttr("title", "列表");
		setAttr("blogList", blogList);
		renderFreeMarker("/freemarker/blog.ftl");
	}
	
	/**
	 * CacheKit的get()方法提供了一个IDataLoader接口，接口中load方法在缓存值不存在时才会被调用
	 */
	public void listByCacheKitLoader() {
		setAttr("title", "标题-loader");
		List<Blog> blogList = CacheKit.get("cacheBlogList", "blogList", new IDataLoader() {
			@Override
			public Object load() {
				return Blog.dao.find("select * from t_blog");
			}
		});
		setAttr("blogList", blogList);
		renderFreeMarker("/freemarker/blog.ftl");
	}
	
	public void removeList() {
		CacheKit.removeAll("cacheBlogList");
		renderText("ok");
	}
	
	public void injectBlog(){
		//为enhance方法传入的拦截器称为Inject拦截器，下面代码中的Tx称为Inject拦截器
		//使用此拦截器可以无侵入的对目标进行AOP增强
		//inject拦截器被认为class级别的拦截器，不过次序在Class级别拦截器之前
		BlogService service = Enhancer.enhance(BlogService.class, Tx.class);
		service.inject();
		renderText("injectBlog");
	}
	
	/**
	 * 表关联操作
	 */
	public void relation() {
		String sql = "select b.*, u.user_name from t_blog b inner join t_user u on b.user_id = u.userId where b.blog_id = ?";
		Blog blog = Blog.dao.findFirst(sql, 1);
		String name = blog.getStr("user_name");
		renderText(name);
	}
	
	/**
	 * 表关联操作，通过Blog中getUser()方法获取相关的User
	 */
	public void getUser() {
		Blog blogDao = new Blog();
		blogDao = blogDao.findById("5");
		User user = blogDao.getUser();
		renderText(user.getUserName());
	}
	
}
