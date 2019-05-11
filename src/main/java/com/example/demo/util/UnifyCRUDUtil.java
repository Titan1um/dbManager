package com.example.demo.util;
import com.example.demo.dao.DBManger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.Stack;

/**
 * @description: 反射 统一CRUD接口
 * @create: 2019-04-21 21:36
 * @author: jun
 */
@Component
public class UnifyCRUDUtil {

	private DBManger dbManger;

	/**
	 * @Description: 根据表名和操作类找返回对应方法
	 * @Param: [operation_table, operation]
	 * @Return: void
	 * @Date: 2019/4/21 21:42
	 */
	public String unify(String operation_table, String operation, String data) throws Exception {
		//根据表名和操作类找返回对应方法
		char[] clazz_chars = operation_table.toCharArray();
		String clazz = "";
		for (int i = 0; i < clazz_chars.length - 1; i++) {
			if (i == 0) {
				clazz += clazz_chars[i];
				clazz = clazz.toUpperCase();
				continue;
			}
			clazz += clazz_chars[i];
		}
		Class targetClazz = null;
		try {
			targetClazz = Class.forName("com.example.demo.models." + clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//根据operation设置调用方法名
		String method_name = "";
		String parser_name = "parseResultSet";

		//设置反射方法调用时参数类型
		Class db_method_param_clazz = null;
		//设置反射方法调用时参数类型
		boolean dataOrInt = false;
		switch (operation) {
			case "0":
				method_name = "insert" + clazz;
				db_method_param_clazz = String.class;
				dataOrInt = true;
				break;
			case "1":
				method_name = "read" + clazz;
				db_method_param_clazz = int.class;
				dataOrInt = false;
				break;
			case "2":
				method_name = "update" + clazz;
				db_method_param_clazz = String.class;
				dataOrInt = true;
				break;
			case "3":
				method_name = "delete" + clazz;
				db_method_param_clazz = int.class;
				dataOrInt = false;
				break;
			default:
				try {
					throw new Exception("Wrong operation.");
				} catch (Exception e) {
					e.printStackTrace();
				}

		}

		//取得model类的parseRes方法
		Method parser_method = targetClazz.getDeclaredMethod(parser_name, ResultSet.class);
		//取得dbManager的操作方法
		Class db_clazz = DBManger.class;
		Method db_method = db_clazz.getDeclaredMethod(method_name, db_method_param_clazz);


		//invoke返回出来的类型不进行转化会是object，可以targetClazz.cast()转化
		if (dataOrInt) {
			return String.valueOf((boolean) db_method.invoke(dbManger, data));
		} else if (operation.equals("1")) {
			return ToJsonUtil.getJson((Stack) (parser_method.invoke(targetClazz.newInstance(), db_method.invoke(dbManger, new JSONObject(data).optInt("id")))));
		} else if (operation.equals("3")){
			return String.valueOf((boolean) db_method.invoke(dbManger,new JSONObject(data).optInt("id") ));
		} else {
			try {
				throw new Exception("Wrong operation.");
			} catch (Exception e) {
				e.printStackTrace();
				return "Wrong operation.";
			}
		}

	}


	@Autowired
	public UnifyCRUDUtil(DBManger dbManger) {
		this.dbManger = dbManger;
	}
}
