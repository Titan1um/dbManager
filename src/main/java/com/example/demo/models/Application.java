package com.example.demo.models;

/**
 * @description: 申请
 * @create: 2019-05-11 21:44
 * @author: jun
 */
public class Application {
	private int id;
	private String apply_time;
	private String use_time;
	private String assosiation;
	private String reason;
	private String information;
	private String member;
	private String status;
	private String reject_message_1;
	private String reject_message_2;
	private String reject_message_3;
	private String classroom;
	private String ismedia;
	private String member_phone;
	private String classnum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApply_time() {
		return apply_time;
	}

	public void setApply_time(String apply_time) {
		this.apply_time = apply_time;
	}

	public String getUse_time() {
		return use_time;
	}

	public void setUse_time(String use_time) {
		this.use_time = use_time;
	}

	public String getAssosiation() {
		return assosiation;
	}

	public void setAssosiation(String assosiation) {
		this.assosiation = assosiation;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReject_message_1() {
		return reject_message_1;
	}

	public void setReject_message_1(String reject_message_1) {
		this.reject_message_1 = reject_message_1;
	}

	public String getReject_message_2() {
		return reject_message_2;
	}

	public void setReject_message_2(String reject_message_2) {
		this.reject_message_2 = reject_message_2;
	}

	public String getReject_message_3() {
		return reject_message_3;
	}

	public void setReject_message_3(String reject_message_3) {
		this.reject_message_3 = reject_message_3;
	}

	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	public String getIsmedia() {
		return ismedia;
	}

	public void setIsmedia(String ismedia) {
		this.ismedia = ismedia;
	}

	public String getMember_phone() {
		return member_phone;
	}

	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}

	public String getClassnum() {
		return classnum;
	}

	public void setClassnum(String classnum) {
		this.classnum = classnum;
	}
}
