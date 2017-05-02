package test;
/**
* @author WaldenLu
* @data   2017年4月29日下午8:28:18
* 测试TableUtils建表
*/

import bean.User;
import util.TableUtils;

public class TestMain {
	public static void main(String[] args) {
		String sql = TableUtils.getCreateTableSQL(User.class);
		System.out.println(sql);
	}
}
