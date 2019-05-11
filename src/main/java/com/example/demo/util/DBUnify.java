package com.example.demo.util;

import org.json.JSONObject;

/**
 * @description:
 * @create: 2019-05-11 16:53
 * @author: jun
 */
public class DBUnify {
	//功能：获取参数表，拼接sql，特殊处理id

	private final String selectALLSQL = "select * from [table]";
	private final String replaceSQL = "replace into [table]([arg]) values([val])";
	private final String deleteSQL = "delete from [table] where id = ?";
	private final String selectSQL = "select * from [table] where id = ?";

	public String prepareSQL(String operation, String table, String str) {
		String sql;
		JSONObject json = new JSONObject(str);
		switch (operation) {
			case "0":
			case "2":
				sql = replaceSQL;
				sql = replacer(sql, "[table]", table, false, false);
				sql = replacer(sql, "[arg]", str, true, false);
				sql = replacer(sql, "[val]", str, false, true);
				break;
			case "1":
				if (!json.has("id")) {
					sql = selectALLSQL;
					sql = replacer(sql, "[table]", table, false, false);
				} else if (json.optInt("id") == 0) {
					sql = selectALLSQL;
					sql = replacer(sql, "[table]", table, false, false);
				} else
					sql = selectSQL;
				sql = replacer(sql, "[table]", table, false, false);
				sql = sql.replace("?", String.valueOf(json.optInt("id")));
				break;
			case "3":
				sql = deleteSQL;
				sql = replacer(sql, "[table]", table, false, false);
				sql = sql.replace("?", String.valueOf(json.optInt("id")));
				break;
			default:
				return "";
		}


		return sql;
	}

	private String replacer(String sql, String old, String target, boolean arg, boolean val) {
		//arg代表是否放入参数
		//val代表需不需要加问号

		//val==true
		if (val == true) {
			JSONObject json = new JSONObject(target);
			StringBuilder sb = new StringBuilder();
			json.keySet().forEach(name -> {
				if (name.equals("id"))
					sb.append("'" + String.valueOf(json.optInt(name)) + "'" + ',');
				else
					sb.append("'" + json.optString(name) + "'" + ',');
			});
			String tmp = sb.toString();
			tmp = tmp.substring(0, tmp.length() - 1);
			sql = sql.replace(old, tmp);
			return sql;
		}
		//arg==true
		if (arg == true) {
			JSONObject json = new JSONObject(target);
			StringBuilder sb = new StringBuilder();
			json.keySet().forEach(name -> {
				sb.append(name + ',');
			});
			String tmp = sb.toString();
			if (tmp.length() > 0)
				tmp = tmp.substring(0, tmp.length() - 1);
			sql = sql.replace(old, tmp);
			return sql;
		}
		//else
		sql = sql.replace(old, target);
		return sql;
	}

	public static void main(String[] args) {
		DBUnify dbUnify = new DBUnify();
		System.out.println(dbUnify.prepareSQL("0", "member", "{id:0}"));
		System.out.println(dbUnify.prepareSQL("1", "member", "{id:0}"));
		System.out.println(dbUnify.prepareSQL("2", "member", "{id:0}"));
		System.out.println(dbUnify.prepareSQL("3", "member", "{id:0}"));
	}
}
