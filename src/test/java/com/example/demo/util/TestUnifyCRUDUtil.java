package com.example.demo.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @create: 2019-04-21 23:24
 * @author: jun
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestUnifyCRUDUtil {
	@Autowired
	private UnifyCRUDUtil unifyCRUDUtil;

	@Test
	public void testMain(){
		try {
			System.out.println(unifyCRUDUtil.unify("members","1","{id:3}")+"==================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
