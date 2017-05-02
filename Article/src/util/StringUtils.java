package util;
/**
* @author WaldenLu
* @data   2017年4月22日下午10:08:59
*
*/


public class StringUtils {
	
	public static boolean isEmpty(String str){//isEmpty的作用是判断字符串是否为空
		return null==str || str.equals("") || str.matches("\\s*");
	}
	
	
	/**
	 * 根据defaultValue来初始化
	 * @param content
	 * @param defaultValue
	 * @return
	 */
	public static String defaultValue(String content, String defaultValue) {
		if(isEmpty(content)){
			return defaultValue;//若content没有赋值，则用默认值初始化。
		}
		return content;
	}
	/**
	 * 把数据库字段名改为驼峰方式
	 * @param column
	 * @return
	 */
	public static String columnToProperty(String column){
		if (isEmpty(column)) {
			return "";
		}
		Byte length = (byte) column.length();
		
		StringBuilder sBuilder = new StringBuilder(length);
		for (int j = 0; j < length; j++) {
			if (column.charAt(j) == '_') {
				while (column.charAt(j+1)=='_') {
					j++;
				}
				sBuilder.append((""+column.charAt(++j)).toUpperCase());
			}else{
				sBuilder.append(column.charAt(j));
			}
		}
		return sBuilder.toString();
	}
	
	
	/**
	 * 将string的首字符改成大写
	 * @param str
	 * @return
	 */
	public static String upperCaseFirstCharacter(String str){
		StringBuilder sBuilder = new StringBuilder();
		char[] arr = str.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (i==0) {
				sBuilder.append((arr[i]+"").toUpperCase());
			}else {
				sBuilder.append((arr[i]+""));
			}
		}
		return sBuilder.toString();
	}
}
