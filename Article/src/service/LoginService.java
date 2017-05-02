package service;

import bean.User;
import util.DataBaseUtils;

/**
* @author WaldenLu
* @data   2017年4月30日下午4:13:35
* 用户登录的service
*/
public class LoginService {
	public User getUser(String username) {
		String sql = "select * from t_user where username = ?";
		User user = DataBaseUtils.queryForBean(sql, User.class, username);
		if (user == null) {
			return null;
		}
//		System.out.println(user);
		return user;
	}
}
