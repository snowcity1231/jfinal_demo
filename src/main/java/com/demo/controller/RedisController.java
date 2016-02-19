package com.demo.controller;

import redis.clients.jedis.exceptions.JedisException;

import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;

/**
 * Description: 
 * <p>
 * Copyright: Copyright(C) 2014 公司 Inc. All Rights Reserved.  
 * <p>
 *  Department: zhfzm 平台开发组  
 * <p>
 * @Author xuechen
 * <p>
 * @Version 2.0.0  2016-2-17 下午4:19:57
 */
@Clear
public class RedisController extends Controller {
	
	public void index() {
		try{
			String str = "abc";
//			Redis.use().getJedis().set("a".getBytes(), "b".getBytes());
			Redis.use().set("a", str);
			String s = (String) Redis.use().get("a");
			renderText(s);
		}catch (JedisException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try{
			System.out.println(Redis.use().incr("b"));;
			renderText(String.valueOf(Redis.use().getCounter("b")));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用use()方法切换到另一个redis
	 */
	public void useNews() {
		try{
			//切换到news的redis模块
			String result = Redis.use("news").set("key", "news");
			renderText(result);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void get() {
		try{
			String v1 = (String) Redis.use().get("a");
			System.out.println("value:" + v1);
			renderText(v1);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
