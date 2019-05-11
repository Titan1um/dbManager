package com.example.demo.dao;

import com.example.demo.models.Assosiation;
import com.example.demo.models.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDBManager {

	@Autowired
	DBManger dbManger;

	@Test
	public void testInsertAssosiation() {
		//改版后insert不用model用String
		Assosiation assosiation = new Assosiation();
		assosiation.setName("关爱智障协会");
		assosiation.setInformation("专门关爱智障的一群智障爱好者");
		assosiation.setTeacher("梁非凡");
		assosiation.setTeacher_tel("15692429592");

		String data = "{'name':'关爱智障协会','information':'专门关爱智障的一群智障爱好者','teacher':'梁非凡','teacher_tel':'15692429592'}";
		try {
			dbManger.insertAssosiation(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteAssosiation() {
		Assosiation assosiation = new Assosiation();
		assosiation.setChair(0);
		assosiation.setTeacher("");
		try {
			dbManger.deleteAssosiation(7);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateAssosiation() {
		//改版后insert不用model用String
		Assosiation assosiation = new Assosiation();
		assosiation.setId(1);

		dbManger.updateAssosiation("{'id':1}");
	}

	@Test
	public void testPST() {
		try {
			dbManger.testPST();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertMember() {
		//改版后insert不用model用String
		Member member = new Member();
		member.setYiban_id(2015081037);

		dbManger.insertMember("{'yiban_id':2015081037}");
	}

	@Test
	public void testUpdateMember() {
		//改版后insert不用model用String
		Member member = new Member();
		member.setYiban_id(2015081037);
		member.setGrade(4);
		dbManger.updateMember("{'yiban_id':2015081037,'grade':4}");
	}

	@Test
	public void testReadMebmer() {
		try {
			System.out.println(Member.parseResultSet(dbManger.readMember(3)).pop().getGrade() + "=======");
			System.out.println(Member.parseResultSet(dbManger.readAllMembers()).pop().getGrade() + "=======");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
