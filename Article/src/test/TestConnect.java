package test;
/**
* @author WaldenLu
* @data   2017年4月29日下午9:44:41
*
*/

/*import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;*/

import bean.User;
import util.DataBaseUtils;

public class TestConnect{
	public static void main(String[] args) {
		
/*
		String id = UUID.randomUUID() + "";
		String createTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		DataBaseUtils.update("INSERT INTO t_user(id,username,password,sex,create_time,is_delete,address,telephone) "
		        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", id,"张三",123456,0,createTime,0,"保密","保密");*/

		
	/*	Map<String, Object> map = null;
		try {
			map = DataBaseUtils.queryForMap("select * from t_user");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(map);*/
		
		User user = DataBaseUtils.queryForBean("select * from t_user limit 1", User.class);
		System.out.println(user);
		
	}
	
	
}
