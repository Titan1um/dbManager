package com.example.demo.util;

import com.example.demo.models.Member;
import com.example.demo.models.User;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Stack;

/**
 * @description: ResultSet to Model
 * @create: 2019-05-11 13:08
 * @author: jun
 */
public class ParseResultSet {
	public <T> Stack<T> getStack(T target, ResultSet res) throws Exception {
		Stack<T> stack = new Stack<>();

		ResultSetMetaData metaData = res.getMetaData();//获取键名
		int columnCount = metaData.getColumnCount();
		while(res.next()){
			JSONObject json = new JSONObject();
			for(int i = 1;i<=columnCount;i++){
				if(metaData.getColumnName(i).equals("id"))
					json.put(metaData.getColumnName(i),res.getInt(i));
				else
					json.put(metaData.getColumnName(i),res.getString(i));
			}
			stack.push((T) ParseJson.getModel(target.getClass(),json.toString()));
		}

		return stack;
	}
	public <T> Stack<T> getStack(T target){
		Stack<T> stack = new Stack<T>();
		stack.push(target);
		return stack;
	}

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//		Stack<User> stack = new ParseResultSet().getStack(new User());
		Class tgc = Class.forName("com.example.demo.models.User");
		Stack stack = new ParseResultSet().getStack(tgc.newInstance());
		User user = (User) stack.pop();
		System.out.println(user.getId());
	}
}
