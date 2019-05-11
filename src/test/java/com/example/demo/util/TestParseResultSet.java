package com.example.demo.util;

import com.example.demo.dao.DBManger;
import com.example.demo.models.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Stack;

/**
 * @description:
 * @create: 2019-05-11 15:33
 * @author: jun
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestParseResultSet {
	@Autowired
	DBManger dbManger;

	@Test
	public void testParseResultSet(){
		try {
			Stack<Member> stack = ParseResultSet.getStack(new Member(),dbManger.readAllMembers());
			stack.forEach((Member member )->{
				System.out.println(member.getId());
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
