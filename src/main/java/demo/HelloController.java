package demo;

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
 * @Version 2.0.0  2016-1-7 下午3:36:34
 */
public class HelloController extends Controller {
	public void index() {
		renderText("Hello JFinal World!");
	}
	
	public void method() {
		String param = getPara(1);
		renderText("this is " + param);
	}
	
}
