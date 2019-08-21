package com.example.demo.util;

import com.example.demo.dao.DBManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.Stack;

/**
 * @description:
 * @create: 2019-05-11 20:57
 * @author: jun
 */
@Component
public class UnifyCRUDUtilV2 {

	private ParseResultSet parseResultSet;
	private DBUnify dbUnify;
	private DBManger dbManger;
	private ToJsonUtil toJsonUtil;


	public String unify(String operation_table, String operation, String data) throws Exception {

		String sql = dbUnify.prepareSQL(operation,operation_table,data);

		String ClassName = "";
		char[] tmp = operation_table.toCharArray();
		tmp[0] -= 32;
		ClassName = String.valueOf(tmp);
		ClassName = ClassName.substring(0,ClassName.length()-1);
		Class targetClass = Class.forName("com.example.demo.models."+ClassName);

		switch (operation){
			case "1":
				ResultSet res = dbManger.exec(sql,"yep");
				Stack stack = parseResultSet.getStack(targetClass.newInstance(),res);
				String resStr = ToJsonUtil.getJson(stack);
				return resStr;
			case "0":
			case "2":
			case "3":
				boolean resBoolean = dbManger.exec(sql);
				return "[{\"status\":200,\"data\":\"done!\"}]";
				default:
					return "[{\"status\":400,\"data\":\"Illegal operation!\"}]";
		}

	}

	@Autowired
	public UnifyCRUDUtilV2(DBManger dbManger){
		dbUnify = new DBUnify();
		parseResultSet = new ParseResultSet();
		toJsonUtil = new ToJsonUtil();
		this.dbManger = dbManger;
	}
}
