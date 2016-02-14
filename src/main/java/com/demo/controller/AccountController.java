package com.demo.controller;

import com.demo.model.Account;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;

/**
 * Description: 
 * <p>
 * Copyright: Copyright(C) 2014 公司 Inc. All Rights Reserved.  
 * <p>
 *  Department: zhfzm 平台开发组  
 * <p>
 * @Author xuechen
 * <p>
 * @Version 2.0.0  2016-1-27 下午4:28:14
 */
public class AccountController extends Controller {
	
	/**
	 * 声明Tx拦截器，为action添加事务支持
	 */
	@Before(Tx.class)
	public void trans() {
		//获取参数
		int fromAccountId = getParaToInt("fromAccountId");
		int toAccountId = getParaToInt("toAccountId");
		int amount = getParaToInt("amount");
		//转出操作
		Db.update("update t_account set cash = cash - ? where account_id = ?", amount, fromAccountId);
		//转入操作
		Db.update("update t_account set cash = cash + ? where account_id = ?", amount, toAccountId);
		
		/*Account from = new Account().findById(fromAccountId);
		Account to = new Account().findById(toAccountId);
		from.setCash(from.getCash() - amount);
		from.update();
		to.setCash(to.getCash() - amount);
		to.update();*/
		
		renderText("ok");
	}
	
	/**
	 * 测试在配置文件中添加事务控制
	 */
	public void save(){
		//获取参数
		int fromAccountId = getParaToInt("fromAccountId");
		int toAccountId = getParaToInt("toAccountId");
		int amount = getParaToInt("amount");
		//转出操作
		Db.update("update t_account set cash = cash - ? where account_id = ?", amount, fromAccountId);
		//转入操作
		Db.update("update t_account set cash = cash + ? where account_id = ?", amount, toAccountId);
		
		renderText("ok");
	}
	
	/**
	 * 测试在配置文件中添加事务控制
	 */
	public void update() {
		//获取参数
		int fromAccountId = getParaToInt("fromAccountId");
		int toAccountId = getParaToInt("toAccountId");
		int amount = getParaToInt("amount");
		//转出操作
		Db.update("update t_account set cash = cash - ? where account_id = ?", amount, fromAccountId);
		//转入操作
		Db.update("update t_account set cash = cash + ? where account_id = ?", amount, toAccountId);
		
		renderText("ok");
	}
}
