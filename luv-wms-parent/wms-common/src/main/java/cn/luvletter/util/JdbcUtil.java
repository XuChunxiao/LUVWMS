package cn.luvletter.util;



import cn.luvletter.exception.ApplicationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JDBC工具类
 * Created by ZhangShuzheng on 2017/1/10.
 */
public class JdbcUtil {

	// 定义数据库的链接
	private Connection conn;
	// 定义sql语句的执行对象
	private PreparedStatement pstmt;
	// 定义查询返回的结果集合
	private ResultSet rs;

	private static JdbcUtil jdbcUtil;

	public static JdbcUtil newInstance(){
		if(jdbcUtil == null){
			jdbcUtil = new JdbcUtil(PropertyUtil.getProperty("driver"),
					PropertyUtil.getProperty("url"),
					PropertyUtil.getProperty("username"),
					PropertyUtil.getProperty("password"));
		}
		return jdbcUtil;
	}
	// 初始化
	private JdbcUtil(String driver, String url, String username, String password) {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 更新数据
	public boolean updateByParams(String sql, List params) throws SQLException {
		// 影响行数
		int result = -1;
		pstmt = conn.prepareStatement(sql);
		int index = 1;
		// 填充sql语句中的占位符
		if (null != params && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i ++) {
				pstmt.setObject(index ++, params.get(i));
			}
		}
		result = pstmt.executeUpdate();
		return result > 0 ? true : false;
	}

	// 查询多条记录
	public List<Map<String,Object>> selectByParams(String sql, List params)  {
		List<Map<String,Object>> list = new ArrayList<> ();
		int index = 1;
		try {
			pstmt = conn.prepareStatement(sql);
			if (null != params && !params.isEmpty()) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(index++, params.get(i));
				}
			}
			rs = pstmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int cols_len = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();
				for (int i = 0; i < cols_len; i++) {
					String cols_name = metaData.getColumnLabel(i + 1);
					Object cols_value = rs.getObject(cols_name);
					if (null == cols_value) {
						cols_value = "";
					}
					map.put(cols_name, cols_value);
				}
				list.add(map);
			}
		}catch (SQLException e){
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		return list;
	}
	/**
	 * @Description: 查询多条记录，并转换为bean
	 * @Date: 21:25 2018/2/24
	 */
	public <T> List<T> selectBean(String sql, List params, Class<T> clazz) {
		List<Map<String,Object>> list = this.selectByParams(sql,params);
		List<T> resultList = new ArrayList<T>();

		for(Map<String,Object> var2:list){
			T bean = BeanUtils.map2Bean(var2, clazz);
			resultList.add(bean);
		}
		return resultList;
	}

	// 释放连接
	public void release() {
		try {
			if (null != rs) {
				rs.close();
			}
			if (null != pstmt) {
				pstmt.close();
			}
			if (null != conn) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("释放数据库连接");
	}


}
