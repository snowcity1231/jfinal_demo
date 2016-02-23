package com.demo.controller;

import java.util.Date;

import com.jfinal.core.Controller;
import com.jfinal.i18n.I18n;
import com.jfinal.i18n.Res;

/**
 * Description: 
 * <p>
 * Copyright: Copyright(C) 2014 公司 Inc. All Rights Reserved.  
 * <p>
 *  Department: zhfzm 平台开发组  
 * <p>
 * @Author xuechen
 * <p>
 * @Version 2.0.0  2016-2-23 下午3:54:06
 */
public class MsgController extends Controller{

	/**
	 * 国际化，英文
	 */
	public void index() {
		//通过locale参数en_US得到对应的Res对象
		Res resEn = I18n.use("en_US");
		//直接获取数据
		String msgEn = resEn.get("msg");
		System.out.println(msgEn);
		//获取数据并使用参数格式化
		String msgEnFormat = resEn.format("msg", "james", new Date());
		
		renderText(msgEnFormat);
	}
	
	/**
	 * 国际化，获取中文数据
	 */
	public void getCn() {
		//通过locale残烛zh_CN得到对应的Res对象
		Res resCn = I18n.use("zh_CN");
		//直接获取数据
		String msgCn = resCn.get("msg");
		System.out.println(msgCn);
		//获取数据并使用参数格式化
		String msgCnFormat = resCn.format("msg", "大卫", new Date());
		
		renderText(msgCnFormat);
	}
	
	/**
	 * I18n还可以加载未使用me.setI18nDefaultBaseName() 配置过的资源文件，唯一不同的是
	 * 需要指定baseName参数，下面的例子需要先创建otherRes_en_US.properties文件
	 */
	public void noConfig() {
		try{
			Res otherRes = I18n.use("i18n", "en_US");
			String otherMsg = otherRes.get("msg");
			System.out.println(otherMsg);
			String otherMsgFormat = otherRes.format("msg", "james", "Wednesday");
			renderText(otherMsgFormat);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void renderFreemarker() {
		try{
			renderFreeMarker("/freemarker/msg.ftl");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
