package com.example.demo.util;

import com.example.demo.models.User;
import com.example.demo.models.test;
import org.json.JSONObject;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 将json转成目标类
 * 调用方法：(T)getModel(T.getClass(),data);
 * @create: 2019-05-11 11:54
 * @author: jun
 */
public class ParseJson {

	public static Object getModel(Class targetClazz, String data) throws Exception {
		JSONObject json = new JSONObject(data);

		//创建实例对象
		Object obj = targetClazz.newInstance();

		//取setter
		//Field[] fields = targetClazz.getDeclaredFields();
		List<Method> methodList = new LinkedList<Method>();
		/*Arrays.stream(fields).forEach((field) -> {
			try {
				methodList.add(new PropertyDescriptor(field.getName(),targetClazz).getWriteMethod());
			} catch (IntrospectionException e) {
				e.printStackTrace();
			}
		});*/
		json.keySet().forEach(name -> {
			String method_name = "set";
			char[] chars = name.toCharArray();
			chars[0] = (chars[0] >= 'a' && chars[0] <= 'z') ? (char) (chars[0] - 32) : chars[0];
//			chars[0] -= 32;
			method_name += String.valueOf(chars);
			try {
				Method method;
				if(name.equals("id"))
					method = targetClazz.getDeclaredMethod(method_name, int.class);
				else
					method = targetClazz.getDeclaredMethod(method_name, String.class);

				//调用setter
				method.invoke(obj, json.opt(name));

			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		});

		//返回对应格式
		return obj;

	}

	public static void main(String[] args) {
		try {
			test tester = (test) getModel(test.class.newInstance().getClass(), "{'test_1':'hi','test_2':'hey'}");
			System.out.println(tester.getTest_1());
			System.out.println(tester.getTest_2());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
