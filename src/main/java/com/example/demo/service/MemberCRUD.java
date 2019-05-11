package com.example.demo.service;

import com.example.demo.dao.DBManger;
import com.example.demo.models.Member;
import com.example.demo.util.ToJsonUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: CRUD service for Member  功能和语法跟所有service.CRUD基本一样
 * @create: 2019-04-21 21:12
 * @author: jun
 */
@Component
public class MemberCRUD {
	private DBManger dbManger;

	public String main(String operation, String data) throws Exception {
		if(operation == null)return "非法请求";
		switch (operation) {
			case "0":
				//注意这里可能插入进去一条空值，当前端提交的json包格式错误时，因为opt了，自动插入空值
				if(data == null) return"缺少data参数";
				return String.valueOf(insertMember(data));
			case "1":
				if(data == null)data="{}";//读操作时，data可以为空或使id=0
				return readMember(new JSONObject(data).optInt("id"));
			case "2":
				if(data == null) return"缺少data参数";//更新操作，提醒前端记得带齐参数
				return String.valueOf(updateMember(data));
			case "3":
				if(data == null) return"缺少data参数";
				return String.valueOf(deleteMember(new JSONObject(data).optInt("id")));
			default:
				return "非法请求";
		}
	}

	public String readMember(int id) throws Exception {
		if(id == 0)return readMember();
		return ToJsonUtil.getJson(Member.parseResultSet(dbManger.readMember(id)));
	}

	public String readMember() throws Exception {
		return ToJsonUtil.getJson(Member.parseResultSet(dbManger.readAllMembers()));
	}

	public boolean insertMember(String data) {
		//需测试空值是Member.parseJson会不会炸 =>opt则ok
		return dbManger.insertMember(data);
	}

	public boolean deleteMember(int id) {
		return dbManger.deleteMember(id);
	}

	public boolean updateMember(String data) {
		return dbManger.updateMember(data);
	}

	/**
	 * @Description: 构造器
	 * @Param: [dbManger]
	 * @Return:
	 * @Date:
	 */
	@Autowired
	public MemberCRUD(DBManger dbManger) {
		this.dbManger = dbManger;
	}

}
