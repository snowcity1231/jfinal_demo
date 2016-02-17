package com.demo.config;


import com.demo.aop.GlobalActionInterceptor;
import com.demo.controller.HelloController;
import com.demo.controller.RenderController;
import com.demo.model._MappingKit;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.tx.TxByActionKeyRegex;
import com.jfinal.plugin.activerecord.tx.TxByActionKeys;
import com.jfinal.plugin.activerecord.tx.TxByMethodRegex;
import com.jfinal.plugin.activerecord.tx.TxByMethods;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;

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
	
	
	/**
	 * 配置JFinal常量值
	 */
	@Override
	public void configConstant(Constants me) {
		//第一次使用use加载的配置将成为主配置，可以通过PropKit.get(...)直接取值
		PropKit.use("JFinal_config.properties");
		//开发模式配置
		me.setDevMode(PropKit.getBoolean("devMode"));
		//默认视图配置为JSP
		me.setViewType(ViewType.JSP);
//		me.setViewType(ViewType.FREE_MARKER);
		
	}

	/**
	 * 配置JFinal路由
	 */
	@Override
	public void configRoute(Routes me) {
		//使用controllerKey访问路由
		me.add("/hello", HelloController.class);
		me.add("/render", RenderController.class, "/jsp");
		
		//将路由进行拆分设置
		me.add(new FrontRoutes());		//前端路由
		me.add(new AdminRoutes());		//后端路由
	}

	/**
	 * 配置插件
	 */
	@Override
	public void configPlugin(Plugins me) {
		
		// 非第一次使用use加载的配置，需要通过每次使用use来指定配置文件名再来取值
		//麻烦！
//		String redisHost = PropKit.use("redis_config.properties").get("host");
//		int redisPort = PropKit.use("redis_config.properties").getInt("port");
//		RedisPlugin rp = new RedisPlugin("myRedis", redisHost, redisPort);
//		log.info("RedisPlugin 配置完成");
//		me.add(rp);
		
		// 非第一次使用 use加载的配置，也可以先得到一个Prop对象，再通过该对象来获取值
		Prop p = PropKit.use("db_config.properties");
		DruidPlugin dp = new DruidPlugin(p.get("url"), p.get("username"), p.get("password"), p.get("driverClassName"));
		System.out.println("DruidPlugin 配置完成");
		me.add(dp);
		
		//指定configName为ds1
		ActiveRecordPlugin arp = new ActiveRecordPlugin("ds1", dp);
		_MappingKit.mapping(arp);
		me.add(arp);
//		//表的主键名默认为"id"
//		arp.addMapping("blog", Blog.class);
//		//表的主键名称为"userId"
//		arp.addMapping("user", "userId", User.class);
//		me.add(arp);
		
		//配置多数据源
		DruidPlugin dp2 = new DruidPlugin(p.get("url2"), p.get("username"), p.get("password"), p.get("driverClassName"));
		me.add(dp2);
		//指定configName为ds1
		ActiveRecordPlugin arp2 = new ActiveRecordPlugin("ds2", dp2);
		_MappingKit.mapping(arp2);
		me.add(arp2);
		
		//配置缓存插件
		me.add(new EhCachePlugin("src/main/resources/ehcache.xml"));
	}

	/**
	 * 配置全局拦截器
	 */
	@Override
	public void configInterceptor(Interceptors me) {
		//添加控制层全局拦截器
		me.addGlobalActionInterceptor(new GlobalActionInterceptor());
		
		//添加业务层全局拦截器
		//me.addGlobalServiceInterceptor();
		
		//兼容老版本方法，功能与addGlobalActionInterceptor一样
//		me.add(new GlobalActionInterceptor());
		
		//通过正则对method进行拦截并开启事务
		//me.add(new TxByMethodRegex("(.*save*.|.*update*.)"));
		//对指定method进行拦截并开启事务
		//me.add(new TxByMethods("save", "update"));
		//通过正则对action进行拦截
		//me.add(new TxByActionKeyRegex("/trans.*"));
		//对指定的actionKey进行拦截
		me.add(new TxByActionKeys("/account/trans", "/account/save","/account/update"));
	}

	/**
	 * 配置处理器
	 */
	@Override
	public void configHandler(Handlers me) {}
	
	/**
	 * 在系统启动后回调该方法
	 */
	@Override
	public void afterJFinalStart() {
		System.out.println("jfinal_demo 已启动...");
	}

	/**
	 * 在系统关闭前回调该方法
	 */
	@Override
	public void beforeJFinalStop() {
		System.out.println("jfinal_demo 已关闭");
	}

}
