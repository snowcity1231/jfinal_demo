package com.demo.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * Description: 
 * <p>
 * Copyright: Copyright(C) 2014 公司 Inc. All Rights Reserved.  
 * <p>
 *  Department: zhfzm 平台开发组  
 * <p>
 * @Author xuechen
 * <p>
 * @Version 2.0.0  2016-1-18 下午2:48:45
 */
public class Blog extends Model<Blog> {
	
	public static final Blog me = new Blog();
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	
}
