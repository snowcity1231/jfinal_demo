package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.demo.aop.DemoInterceptor;
import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

/**
 * Description: 
 * <p>
 * Copyright: Copyright(C) 2014 公司 Inc. All Rights Reserved.  
 * <p>
 *  Department: zhfzm 平台开发组  
 * <p>
 * @Author xuechen
 * <p>
 * @Version 2.0.0  2016-1-7 下午5:28:58
 */

public class IndexController extends Controller {
	public void index() {
		renderText("index");
	}
	
	/**
	 * getPara系列方法
	 */
	public String showPara(){
		//返回 url 请求中的 urlPara 参数的整体值
		//注意：约定字母 N与 n可以表示负 号，这对urlParaSeparator为“-”时非常有用
		String para = getPara();
		
		//System.out.println(getParaToInt(2));	//参数为2-5-N8时，输出-8
		renderText(para);
		return para;
	}
	
	public void list() {
		//返回 url 请求中的 urlPara 参数的第一个值
		String strNum = getPara(0);	
		List<Integer> list = new ArrayList<Integer>();
		int n = 0;
		if(strNum != null){
			//返回 url 请求中的 urlPara 参数的第一个值并转化成int值
			n = getParaToInt(0);	
		}
		if(n > 0){
			for(int i=1; i<=n; i++){
				list.add(i);
			}
		}
		if(list.size() > 0){
			renderText(list.toString());
		}else{
			renderText("no list");
		}
	}
}
