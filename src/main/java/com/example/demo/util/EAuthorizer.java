package com.example.demo.util;

import cn.yiban.open.Authorize;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import cn.yiban.util.HTTPSimple;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/**
 * @Description 判断是易班回调还是登陆的授权认证，做对应返回的具体工具类
 * @Author jun
 */
public class EAuthorizer {

	private static String appKey  = "4bbde0c36316ff6d";
	private static String appSecret = "9f6f98d79c275a2a40afaea5d171492a";
	//回调地址
	private static String callbackurl = " http://129.204.150.195:8888/yiban";
	private static Authorize authorize = new Authorize(appKey, appSecret);

	/**
	 * @Description 获取授权重定向地址
	 * @Author jun
	 * @Param response
	 */
	public static void parseCallback(HttpServletResponse response){
		String url = authorize.forwardurl(callbackurl, "test", Authorize.DISPLAY_TAG_T.WEB);
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 授权完成 解析得出acess_token
	 * @Param: [code]
	 * @Return: java.lang.String
	 * @Date: 2019/4/20 3:56
	 */
	public static String parseCallback(String code,HttpServletResponse resp){
		System.out.println("code="+code);
		String text = authorize.querytoken(code, callbackurl);
		System.out.println("text="+text);
		String url = "http://129.204.150.195:8080/index.html?data="+text;
		try {
			JSONObject jsonObject = new JSONObject(text);
			if (jsonObject.has("access_token")) {
				resp.sendRedirect(url);
			}
			System.out.println("url="+url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}

}
