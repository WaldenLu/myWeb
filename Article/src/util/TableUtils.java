package util;

import java.lang.reflect.Field;



import annotation.Column;
import annotation.Table;

/**
* @author WaldenLu
* @data   2017年4月22日下午10:09:17
* 根据JavaBean中内容生成MySQL建表语句
*/
public class TableUtils {
	
	public static String getCreateTableSQL(Class<?> clazz) {
		StringBuffer sBuffer = new StringBuffer();
		//获取表名
		Table table = (Table) clazz.getAnnotation(Table.class);
		//Returns this element's annotation for the specified type if such an annotation is present, else null.				
		String tableName = table.tableName();
		sBuffer.append("DROP TABLE IF EXISTS ").append(tableName).append(";\n");
		sBuffer.append("create table ");
		
		sBuffer.append(tableName).append("(\n");
//CREATE TABLE table_name (
		Field[] fields = clazz.getDeclaredFields();
		String primaryKey = "";
		//遍历所有字段
		for (int i = 0; i < fields.length; i++) {
			Column column = (Column) fields[i].getAnnotations()[0];//public Annotation[] getAnnotations()
			String field = column.field();
			String type = column.type();
			boolean defaultNull = column.defaultNull();
			
			sBuffer.append("\t" + field).append(" ").append(type);
//column_name column_type)
			if (defaultNull) {
				if (type.toUpperCase().equals("TIMESTAMP")) {
					sBuffer.append(",\n");
				}else {
					sBuffer.append(" DEFAULT NULL,\n");
				}
			}else {
				sBuffer.append(" NOT NULL,\n");
				if (column.primaryKey()) {
					primaryKey = "PRIMARY KEY (" + field + ")";
				}
			}
		}
		
		if (!StringUtils.isEmpty(primaryKey)) {
			sBuffer.append("\t").append(primaryKey);
		}
		sBuffer.append("\n) DEFAULT CHARSET=utf8");
		
		return sBuffer.toString();
	}
}
