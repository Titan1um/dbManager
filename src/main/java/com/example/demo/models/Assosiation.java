package com.example.demo.models;

import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

/**
 * @description: impl for assosiation.interface
 * @create: 2019-04-20 02:43
 * @author: jun
 */
public class Assosiation{

	private int id;
	private String name;
	private String information;
	private int chair;
	private String teacher;
	private String teacher_tel;

	/**
	 * @Description: 将查询获得的resultset转换成Assosiation类
	 * @Param: [res]
	 * @Return: java.util.List<com.example.demo.models.Assosiation>
	 * @Date: 2019/4/20 5:08
	 */
	public static Stack<Assosiation> parseResultSet(ResultSet res) throws SQLException {
		Stack<Assosiation> stack = new Stack<>();

		while(res.next()){
			Assosiation assosiation = new Assosiation();
			assosiation.setId(res.getInt("id"));
			assosiation.setChair(res.getInt("chair"));
			assosiation.setName(res.getString("name"));
			assosiation.setInformation(res.getString("information"));
			assosiation.setTeacher(res.getString("teacher"));
			assosiation.setTeacher_tel(res.getString("teacher_tel"));

			stack.push(assosiation);
		}

		return stack;
	}

	public static Assosiation parseJson(String str){
		JSONObject json = new JSONObject(str);
		Assosiation assosiation = new Assosiation();
		assosiation.setId(json.optInt("id"));
		assosiation.setChair(json.optInt("chair"));
		assosiation.setName(json.optString("name"));
		assosiation.setInformation(json.optString("information"));
		assosiation.setTeacher(json.optString("teacher"));
		assosiation.setTeacher_tel(json.optString("teacher_tel"));

		return assosiation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getChair() {
		return chair;
	}

	public void setChair(int chair) {
		this.chair = chair;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getTeacher_tel() {
		return teacher_tel;
	}

	public void setTeacher_tel(String teacher_tel) {
		this.teacher_tel = teacher_tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
}
