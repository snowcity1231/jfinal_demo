package com.demo.util;

import javax.sql.DataSource;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;

/**
 * GeneratorDemo
 * 针对多数据源，当数据源2表结构与数据源1不一样，需要重新生成model
 * 注意：_MappingKit配置不能与数据源1一致
 */
public class GeneratorDemoDS2 {
	
	public static DataSource getDataSource() {
//		Prop p = PropKit.use("a_little_config.txt");
//		C3p0Plugin c3p0Plugin = new C3p0Plugin(p.get("jdbcUrl"), p.get("user"), p.get("password"));
//		c3p0Plugin.start();
		Prop p = PropKit.use("db_config.properties");
		DruidPlugin dp = new DruidPlugin(p.get("url2"), p.get("username"), p.get("password"), p.get("driverClassName"));
		dp.start();
		return dp.getDataSource();
	}
	
	public static void main(String[] args) {
		// base model 所使用的包名
		String baseModelPackageName = "com.demo.base2";
		// base model 文件保存路径
		String rootPath = PathKit.getWebRootPath();
		String javaPath = rootPath.replace("webapp", "java");
		
		String baseModelOutputDir = javaPath + "\\com\\demo\\base2";
		
		// model 所使用的包名 (MappingKit 默认使用的包名)
		String modelPackageName = "com.demo.model2";
		// model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
		String modelOutputDir = javaPath + "\\com\\demo\\model2";
		
		// 创建生成器
		Generator gernerator = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
		// 设置数据库方言
		gernerator.setDialect(new MysqlDialect());
		// 添加不需要生成的表名
		gernerator.addExcludedTable("");
		// 设置是否在 Model 中生成 dao 对象
		gernerator.setGenerateDaoInModel(true);
		// 设置是否生成字典文件
		gernerator.setGenerateDataDictionary(false);
		// 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
		gernerator.setRemovedTableNamePrefixes("t_");
		// 生成
		gernerator.generate();
	}
}




