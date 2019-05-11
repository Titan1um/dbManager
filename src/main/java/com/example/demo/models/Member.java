package com.example.demo.models;

import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

/**
 * @description: 成员表
 * @create: 2019-04-21 20:42
 * @author: jun
 */
public class Member {


	private int id;
	private String yiban_id;
	private String sex;
	private String college;
	private String sno;
	private String assosiation;
	private String grade;
	private String position;

	/**
	* @Description: ResultSet转Member
	* @Param: [res]
	* @Return: java.util.Stack<com.example.demo.models.Member>
	* @Date: 2019/4/21 20:45
	*/
	public static Stack<Member> parseResultSet(ResultSet res) throws SQLException {
		Stack<Member> stack = new Stack<>();

		while(res.next()){
			Member member = new Member();
			member.setId(res.getInt("id"));
			member.setYiban_id(res.getString("yiban_id"));
			member.setSex(res.getString("sex"));
			member.setCollege(res.getString("college"));
			member.setSno(res.getString("sno"));
			member.setAssosiation(res.getString("assosiation"));
			member.setGrade(res.getString("grade"));
			member.setPosition(res.getString("position"));
			stack.push(member);
		}

		return stack;
	}

	public static Member parseJson(String str){
		JSONObject json = new JSONObject(str);
		Member member = new Member();
		member.setId(json.optInt("id"));
		member.setYiban_id(json.optString("yiban_id"));
		member.setSex(json.optString("sex"));
		member.setCollege(json.optString("college"));
		member.setSno(json.optString("sno"));
		member.setAssosiation(json.optString("assosiation"));
		member.setGrade(json.optString("grade"));
		member.setPosition(json.optString("position"));

		return member;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYiban_id() {
		return yiban_id;
	}

	public void setYiban_id(String yiban_id) {
		this.yiban_id = yiban_id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getAssosiation() {
		return assosiation;
	}

	public void setAssosiation(String assosiation) {
		this.assosiation = assosiation;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
