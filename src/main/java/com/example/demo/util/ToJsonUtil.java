package com.example.demo.util;

import com.example.demo.models.Assosiation;
import org.json.JSONObject;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Stack;

/**
 * @description: 写个反射工具类，把对象映射成json，懒得逐个类写一个读
 * @create: 2019-04-20 03:50
 * @author: jun
 */
public class ToJsonUtil {


	public static <T> String getJson(T model) throws Exception {
		JSONObject json = new JSONObject();
		
		
		//取类
		Class clazz = model.getClass();
		//遍历成员变量
		for(Field field: clazz.getDeclaredFields()){
			String field_name = field.getName();
			if (field_name.equals("SerialVersionUID")){continue;}
			PropertyDescriptor descriptor = new PropertyDescriptor(field_name,clazz);
			Method method = descriptor.getReadMethod();
			Object value = method.invoke(model);
			//若取得变量为空，则用字符串null代替以加入jsonObject
			if(value == null){
				value = "null";
			}
			json.put(field_name,value);
		}
		return json.toString();
	}

	public static <T> String getJson(Stack<T> stack) throws Exception {
		String res = "[";
		if(!stack.empty()){
			res += ToJsonUtil.getJson(stack.pop());
		}
		while (!stack.empty()){
			res += (","+ToJsonUtil.getJson(stack.pop()));
		}
		res +="]";

		return res;
	}
	
	public static void main(String[] args) throws Exception {
		Assosiation assosiation = new Assosiation();
		assosiation.setId(1);
		new ToJsonUtil().getJson(assosiation);
	}
}
