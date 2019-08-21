package com.example.demo.models;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;

/**
 * @description:
 * @create: 2019-05-11 12:43
 * @author: jun
 */
public class test {

	private String test_1;
	private String test_2;

	public String getTest_1() {
		return test_1;
	}

	public void setTest_1(String test_1) {
		this.test_1 = test_1;
	}

	public String getTest_2() {
		return test_2;
	}

	public void setTest_2(String test_2) {
		this.test_2 = test_2;
	}

	public static void main(String[] args){
		float f1 = 0.1f; 
		float f2 = 0.2f; 
		System.out.println(f1+f2);
		
//		String tmp = "PREFid:1SUFF";
//		tmp = tmp.replace("PREF","{");
//		tmp = tmp.replace("SUFF","}");
//		System.out.println(tmp);
	}
}
