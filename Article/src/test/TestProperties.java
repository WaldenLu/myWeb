package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
* @author WaldenLu
* @data   2017年4月29日下午9:13:10
* 测试类，读取jdbc.properties
*/
public class TestProperties {
	public static void main(String[] args) {
		InputStream inputStream = TestProperties.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
			System.out.println(properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
