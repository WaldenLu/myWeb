package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @author WaldenLu
* @data   2017年4月22日下午9:58:22
*
*/

@Target(ElementType.TYPE)    //注解的目标是类
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
	public String tableName();
}
