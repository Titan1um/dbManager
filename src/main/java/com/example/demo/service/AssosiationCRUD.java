package com.example.demo.service;

import com.example.demo.dao.DBManger;
import com.example.demo.models.Assosiation;
import com.example.demo.util.ToJsonUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 对社团的crud服务     使用方法：json.put("data",AssosiationCRUD.readAssosiation(id));
 * @create: 2019-04-20 04:30
 * @author: jun
 */
@Component
public class AssosiationCRUD {
	private DBManger dbManger;

	public String main(String operation, String data) throws Exception {
		if(operation == null)return "非法请求";
		switch (operation) {
			case "0":
				//注意这里可能插入进去一条空值，当前端提交的json包格式错误时，因为opt了，自动插入空值
				if(data == null) return"缺少data参数";
				return String.valueOf(insertAssosiation(data));
			case "1":
				if(data == null)data="{}";//读操作时，data可以为空或使id=0
				return readAssosiation(new JSONObject(data).optInt("id"));
			case "2":
				if(data == null) return"缺少data参数";//更新操作，提醒前端记得带齐参数
				return String.valueOf(updateAssosiation(data));
			case "3":
				if(data == null) return"缺少data参数";
				return String.valueOf(deleteAssosiation(new JSONObject(data).optInt("id")));
			default:
				return "非法请求";
		}
	}

	public String readAssosiation(int id) throws Exception {
		if(id == 0)return readAssosiation();
		return ToJsonUtil.getJson(Assosiation.parseResultSet(dbManger.readAssosiation(id)));
	}

	public String readAssosiation() throws Exception {
		return ToJsonUtil.getJson(Assosiation.parseResultSet(dbManger.readAllAssosiations()));
	}

	public boolean insertAssosiation(String data) {
		//需测试空值是Assosiation.parseJson会不会炸 =>opt则ok
		return dbManger.insertAssosiation(data);
	}

	public boolean deleteAssosiation(int id) {
		return dbManger.deleteAssosiation(id);
	}

	public boolean updateAssosiation(String data) {
		return dbManger.updateAssosiation(data);
	}

	/**
	 * @Description: 构造器
	 * @Param: [dbManger]
	 * @Return:
	 * @Date:
	 */
	@Autowired
	public AssosiationCRUD(DBManger dbManger) {
		this.dbManger = dbManger;
	}

}



