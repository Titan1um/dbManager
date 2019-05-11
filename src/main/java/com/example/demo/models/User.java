package com.example.demo.models;

import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

/**
 * @description: user
 * @create: 2019-05-11 11:38
 * @author: jun
 */
public class User {
	private int id;
	private String access_token;
	private String member_id;
	private String yiban_id;
	private String assosiation;
	private String position;
	private String password;
	private String user;
	private String head;

	public static Stack<User> parseResultSet(ResultSet res) throws SQLException {
		Stack<User> stack = new Stack<>();

		while(res.next()){
			User user = new User();
			user.setId(res.getInt("id"));
			user.setAccess_token(res.getString("access_token"));
			user.setMember_id(res.getString("member_id"));
			user.setYiban_id(res.getString("yiban_id"));
			user.setAssosiation(res.getString("assosiation"));
			user.setPosition(res.getString("position"));
			user.setPassword(res.getString("password"));
			user.setUser(res.getString("user"));
			user.setHead(res.getString("head"));
			stack.push(user);
		}

		return stack;
	}

	public static User parseJson(String str){
		JSONObject json = new JSONObject(str);
		User user = new User();
		user.setId(json.optInt("id"));
		user.setAccess_token(json.optString("access_token"));
		user.setMember_id(json.optString("member_id"));
		user.setYiban_id(json.optString("yiban_id"));
		user.setAssosiation(json.optString("assosiation"));
		user.setPosition(json.optString("position"));
		user.setPassword(json.optString("password"));
		user.setUser(json.optString("user"));
		user.setHead(json.optString("head"));

		return user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getYiban_id() {
		return yiban_id;
	}

	public void setYiban_id(String yiban_id) {
		this.yiban_id = yiban_id;
	}

	public String getAssosiation() {
		return assosiation;
	}

	public void setAssosiation(String assosiation) {
		this.assosiation = assosiation;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}
}
