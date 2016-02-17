package com.demo.noweb;

import com.demo.model.Blog;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;

/**
 * Description: 
 * <p>
 * Copyright: Copyright(C) 2014 公司 Inc. All Rights Reserved.  
 * <p>
 *  Department: zhfzm 平台开发组  
 * <p>
 * @Author xuechen
 * <p>
 * @Version 2.0.0  2016-2-17 下午2:45:23
 */
public class ActiveRecordTest {
	
	public static void main(String[] args) {
		// 非第一次使用 use加载的配置，也可以先得到一个Prop对象，再通过该对象来获取值
		Prop p = PropKit.use("db_config.properties");
		DruidPlugin dp = new DruidPlugin(p.get("url"), p.get("username"), p.get("password"), p.get("driverClassName"));
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		arp.addMapping("t_blog", "blog_id", Blog.class);
		
		// 与web环境唯一的不同是要手动调用一次相关插件的start()方法
		dp.start();
		arp.start();
		
		Blog blog = Blog.dao.findById(1);
		System.out.println(blog.getTitle());
	}
}
