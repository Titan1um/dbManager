package com.example.demo.models;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

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
		HttpGet httpGet = new HttpGet("http://129.204.150.195:8888/v2?operation=1&data={id:0,classroom:hihihihi,use_time:tomorrow}&operation_table=members&sign=test");
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			CloseableHttpResponse response = client.execute(httpGet);
			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
