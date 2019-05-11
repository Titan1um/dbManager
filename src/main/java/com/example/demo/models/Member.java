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
	private int yiban_id;
	private int sex;
	private String college;
	private int sno;
	private int assosiation;
	private int grade;
	private int position;

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
			member.setYiban_id(res.getInt("yiban_id"));
			member.setSex(res.getInt("sex"));
			member.setCollege(res.getString("college"));
			member.setSno(res.getInt("sno"));
			member.setAssosiation(res.getInt("assosiation"));
			member.setGrade(res.getInt("grade"));
			member.setPosition(res.getInt("position"));
			stack.push(member);
		}

		return stack;
	}

	public static Member parseJson(String str){
		JSONObject json = new JSONObject(str);
		Member member = new Member();
		member.setId(json.optInt("id"));
		member.setYiban_id(json.optInt("yiban_id"));
		member.setSex(json.optInt("sex"));
		member.setCollege(json.optString("college"));
		member.setSno(json.optInt("sno"));
		member.setAssosiation(json.optInt("assosiation"));
		member.setGrade(json.optInt("grade"));
		member.setPosition(json.optInt("position"));

		return member;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYiban_id() {
		return yiban_id;
	}

	public void setYiban_id(int yiban_id) {
		this.yiban_id = yiban_id;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getAssosiation() {
		return assosiation;
	}

	public void setAssosiation(int assosiation) {
		this.assosiation = assosiation;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
