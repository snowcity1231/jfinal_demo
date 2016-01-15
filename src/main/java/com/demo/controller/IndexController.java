package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

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
	
	public void list() {
		String strNum = getPara(0);
		List<Integer> list = new ArrayList<Integer>();
		int n = 0;
		if(strNum != null){
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
