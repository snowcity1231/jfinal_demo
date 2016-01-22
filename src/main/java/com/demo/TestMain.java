package com.demo;

import com.demo.service.BlogService;
import com.jfinal.aop.Duang;
import com.jfinal.aop.Enhancer;

/**
 * Description: 
 * <p>
 * Copyright: Copyright(C) 2014 公司 Inc. All Rights Reserved.  
 * <p>
 *  Department: zhfzm 平台开发组  
 * <p>
 * @Author xuechen
 * <p>
 * @Version 2.0.0  2016-1-22 下午4:32:30
 */
public class TestMain {
	
	public static void main(String[] args) {
		//使用Duang.duang方法在任何地方对目标进行增强，让其拥有AOP能力
		BlogService service = Duang.duang(BlogService.class);
		service.info(2);
		
		//使用Enhancer.enhance方法同上
		BlogService service2 = Enhancer.enhance(BlogService.class);
		service2.info(333);
	}
}
