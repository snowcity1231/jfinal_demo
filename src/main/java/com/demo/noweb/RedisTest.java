package com.demo.noweb;

import com.jfinal.plugin.redis.Redis;
import com.jfinal.plugin.redis.RedisPlugin;

/**
 * Description: 
 * <p>
 * Copyright: Copyright(C) 2014 公司 Inc. All Rights Reserved.  
 * <p>
 *  Department: zhfzm 平台开发组  
 * <p>
 * @Author xuechen
 * <p>
 * @Version 2.0.0  2016-2-17 下午4:55:21
 */
public class RedisTest {

	public static void main(String[] args) {
		RedisPlugin bbsRedis = new RedisPlugin("bbs", "localhost", 6379, "123456");
		bbsRedis.start();
		
		String str = "abc";
		Redis.use().set("a", str);
		String s = (String) Redis.use().get("a");
		System.out.println(s);
	}
}
