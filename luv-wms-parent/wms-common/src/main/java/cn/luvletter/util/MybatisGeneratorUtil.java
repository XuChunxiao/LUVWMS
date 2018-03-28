package cn.luvletter.util;

import org.apache.commons.lang.ObjectUtils;
import org.apache.velocity.VelocityContext;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 代码生成类
 * Created by ZhangShuzheng on 2017/1/10.
 */
public class MybatisGeneratorUtil {

	public static void main(String[] args) throws Exception {
		String jdbc_driver="com.mysql.jdbc.Driver";
		String jdbc_url="jdbc:mysql://localhost:3306/";
		String jdbc_username="root";
		String jdbc_password="123456";
		String module="luv-wms-parent";
		String database="luv_wms";
		String table_prefix="wms_base_";
		String package_name="cn.luvletter.base";
		String tableName="wms_base_goods";
		generator(jdbc_driver,
				jdbc_url,
				jdbc_username,
				jdbc_password,
				module,
				database,
				table_prefix,
				package_name,
				tableName);
	}

	// generatorConfig模板路径
	private static String generatorConfig_vm = "D:\\code\\JAVA\\毕业设计\\WMS\\luv-wms-parent\\wms-common\\src\\main\\resources\\template\\generatorConfig.vm";
	// Service模板路径
	private static String service_vm = "/template/Service.vm";
	// ServiceMock模板路径
	private static String serviceMock_vm = "/template/ServiceMock.vm";
	// ServiceImpl模板路径
	private static String serviceImpl_vm = "/template/ServiceImpl.vm";

	/**
	 * 根据模板生成generatorConfig.xml文件
	 * @param jdbc_driver   驱动路径
	 * @param jdbc_url      链接
	 * @param jdbc_username 帐号
	 * @param jdbc_password 密码
	 * @param module        项目模块
	 * @param database      数据库
	 * @param table_prefix  表前缀
	 * @param package_name  包名
	 */
	public static void generator(
			String jdbc_driver,
			String jdbc_url,
			String jdbc_username,
			String jdbc_password,
			String module,
			String database,
			String table_prefix,
			String package_name,
			String tableName) throws Exception{

		//generatorConfig_vm = MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath().replaceFirst("/", "");
		//service_vm = MybatisGeneratorUtil.class.getResource(service_vm).getPath().replaceFirst("/", "");
		//serviceMock_vm = MybatisGeneratorUtil.class.getResource(serviceMock_vm).getPath().replaceFirst("/", "");
		//serviceImpl_vm = MybatisGeneratorUtil.class.getResource(serviceImpl_vm).getPath().replaceFirst("/", "");

		String targetProject = module + "/" + "wms-api";
		//String basePath = MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "").replace(targetProject, "").replaceFirst("/", "");
		String generatorConfig_xml = module + "/wms-common/src/main/resources/generatorConfig.xml";;
		//targetProject = basePath + targetProject;
		String sql = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + database + "' AND table_name LIKE CONCAT('%','" + table_prefix + "','%')";

		System.out.println("========== 开始生成generatorConfig.xml文件 ==========");
		List<Map<String, Object>> tables = new ArrayList<>();
		try {
			VelocityContext context = new VelocityContext();
			Map<String, Object> table;

			// 查询定制前缀项目的所有表
			JdbcUtil jdbcUtil = JdbcUtil.newInstance();
			List<Map<String,Object>> result = jdbcUtil.selectByParams(sql, null);
			for (Map map : result) {
				if(tableName!=null && !tableName.equals(map.get("table_name").toString())){
					continue;
				}
				System.out.println(map.get("table_name"));
				table = new HashMap<>();
				table.put("table_name", map.get("table_name"));
				table.put("model_name", lineToHump(ObjectUtils.toString(map.get("table_name"))).replace("WmsBase",""));
				tables.add(table);
			}
			jdbcUtil.release();

			String targetProject_sqlMap =  module + "/" + "wms-service";
			context.put("tables", tables);
			context.put("generator_javaModelGenerator_targetPackage", package_name + ".model");
			context.put("generator_sqlMapGenerator_targetPackage", package_name + ".dao.mapper");
			context.put("generator_javaClientGenerator_targetPackage", package_name + ".dao");
			context.put("targetProject", targetProject);
			context.put("targetProject_sqlMap", targetProject_sqlMap);
			VelocityUtil.generate(generatorConfig_vm, generatorConfig_xml, context);
			// 删除旧代码
			//deleteDir(new File(targetProject + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/dao/model"));
			//deleteDir(new File(targetProject + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/dao/mapper"));
			//deleteDir(new File(targetProject_sqlMap + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/dao/mapper"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("========== 结束生成generatorConfig.xml文件 ==========");

		System.out.println("========== 开始运行MybatisGenerator ==========");
		List<String> warnings = new ArrayList<>();
		File configFile = new File(generatorConfig_xml);
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		for (String warning : warnings) {
			System.out.println(warning);
		}
		System.out.println("========== 结束运行MybatisGenerator ==========");

	}

	// 递归删除非空文件夹
	public static void deleteDir(File dir) {
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteDir(files[i]);
			}
		}
		dir.delete();
	}

	/**
	 * 下划线转驼峰
	 * @param str
	 * @return
	 */
	public static String lineToHump(String str) {
		if (null == str || "".equals(str)) {
			return str;
		}
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);

		str = sb.toString();
		str = str.substring(0, 1).toUpperCase() + str.substring(1);

		return str;
	}

	private static Pattern linePattern = Pattern.compile("_(\\w)");

}
