package util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;





/**
* @author WaldenLu
* @data   2017年4月29日下午9:27:57
* 封装一个数据库工具类
*/

public class DataBaseUtils {
	static{
		config("jdbc.properties");
		}
	
	private static String username;
	private static String password;
	private static String dataBaseName;
	/**
	 * 配置数据库的基本信息
	 * @return void
	 */
	public static void config(String path) {
		InputStream inputStream = DataBaseUtils.class.getClassLoader().getResourceAsStream(path);
		Properties properties = new Properties();
		
		try {
			properties.load(inputStream);
			username = properties.getProperty("db.username");
			password = properties.getProperty("db.password");
			dataBaseName = properties.getProperty("db.dataBaseName");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库链接
	 * @return Connection 
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dataBaseName+"?useUnicode=true&characterEncoding=utf8",username,password);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return connection;
		
	}
	
	/**
	 * 关闭资源
	 * @param connection
	 * @param statement
	 * @param rs
	 */
	public static void closeConnection(Connection connection, PreparedStatement statement,
			ResultSet rSet){
		
		if (rSet!=null) {
			try {
				rSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		if (statement!=null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * DML操作
	 * @param sql
	 * @param objects
	 */
	public static void update(String sql, Object...objects){
		Connection connection = getConnection();
		PreparedStatement statement = null;
		
		try {
			statement = (PreparedStatement) connection.prepareStatement(sql);
			for (int i = 0; i < objects.length; i++) {
				statement.setObject(i+1, objects[i]);
			}
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(connection, statement, null);
		}
	}
	/**
	 * 查询出数据，并且list返回
	 * @param sql
	 * @param objects
	 * @return
	 * @throws SQLException
	 */
	public static List<Map<String, Object>> queryForList(String sql, Object...objects){
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Connection connection = getConnection();
		PreparedStatement statement	= null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(sql);
			for (int i = 0; i < objects.length; i++) {
				statement.setObject(i+1, objects[i]);
			}
			rs = statement.executeQuery();
			while (rs.next()) {
				ResultSetMetaData resultSetMetaData = rs.getMetaData();
				int count = resultSetMetaData.getColumnCount();//获取列数
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < count; i++) {
					map.put(resultSetMetaData.getColumnName(i+1), rs.getObject(resultSetMetaData.getColumnName(i+1)));
				}
				result.add(map);
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(connection, statement, rs);
		}
		return result;
	}
	
	/**
	 * 查询出数据，并且map返回
	 * @param sql
	 * @param objects
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, Object> queryForMap(String sql, Object...objects) throws SQLException {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> list = queryForList(sql, objects);
		if (list.size() != 1) {
			result = null;
		}else {
			result = list.get(0);
		}
		return result;
	}

	/**
	 * @param sql
	 * @param clazz
	 * @param objects
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T>T queryForBean(String sql, Class<?> clazz, Object...objects){
		T obj = null;
		Map<String, Object> map = null;
		Field field = null;
		try {
			obj = (T) clazz.newInstance();
			map = queryForMap(sql, objects);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (map == null) {
			return null;
		}
//		遍历map
//		password=123456, update_time=2017-04-30 11:32:53.0, address=保密, headerPic=null, 
//		create_time=2017-04-30 00:00:00.0, sex=0, telephone=保密, id=99df1676-c495-47b6-b626-0dbffad40cce, 
//		email=null, username=张三, is_delete=0}
		for (String columnName : map.keySet()) {
			Method method = null;
			String propertyName = StringUtils.columnToProperty(columnName);//属性名称驼峰显示
			
			try {
				field = clazz.getDeclaredField(propertyName);
				
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}catch (SecurityException e) {
				e.printStackTrace();
			}
			String fieldType = field.toString().split(" ")[1];//获取字段类型 其实可以直接用fieldType()
			Object value = map.get(columnName);
			if (value == null) {
				continue;
			}
			
			String setMethodName = "set" + StringUtils.upperCaseFirstCharacter(propertyName);
			
			try {
				String valueType = value.getClass().getName();
				
				if (!fieldType.equalsIgnoreCase(valueType)) {
//					System.out.println("类型不匹配");
					if (fieldType.equalsIgnoreCase("java.lang.Integer")) {
						value = Integer.parseInt(String.valueOf(value));
					}else if(fieldType.equalsIgnoreCase("java.lang.String")){
						value = String.valueOf(value);
					}else if (fieldType.equalsIgnoreCase("java.util.Date")) {
						valueType = "java.util.Date";
						String dataStr = String.valueOf(value);
						Timestamp ts = Timestamp.valueOf(dataStr);
						Date date = new Date(ts.getTime());
						value = date;
					}
				}
				//获取set方法
				method = clazz.getDeclaredMethod(setMethodName, Class.forName(fieldType));
				//执行set方法
				method.invoke(obj, value);
			} catch (Exception e) {
				
			}
		}
		return obj;
	
	}
}
