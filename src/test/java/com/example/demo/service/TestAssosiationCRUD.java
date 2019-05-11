package com.example.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

/**
 * @description:
 * @create: 2019-04-20 05:26
 * @author: jun
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAssosiationCRUD {
	@Autowired
	AssosiationCRUD assosiationCRUD;

	@Test
	public void testReadOne() {
		try {
			System.out.println("==========Result is:" + assosiationCRUD.readAssosiation(9) + "==========");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadAll() {
		try {
			System.out.println("==========Result is:" + assosiationCRUD.readAssosiation() + "==========");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsert() {
		String data = "{'id':1,'name':'关爱智障协会'}";
		System.out.println(assosiationCRUD.insertAssosiation(data));
	}


	@Test
	public void testDelete() {
		System.out.println(assosiationCRUD.deleteAssosiation(12));
	}

	@Test
	public void testUpdate() {
		String data = "{'id':1,'name':'关爱智障协会'}";
		System.out.println(assosiationCRUD.updateAssosiation(data));
	}
}
