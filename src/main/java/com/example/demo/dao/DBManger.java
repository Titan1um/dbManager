package com.example.demo.dao;

import com.example.demo.models.Assosiation;
import com.example.demo.models.Member;
import com.example.demo.util.ConnectUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * @Description: 所有数据库的操作，没用框架，手撸
 * @Date: 2019/4/20 3:42
 */
@Component
public class DBManger {
	private Connection conn;
	private JSONObject jsonData;


	/**
	 * @Description: 插入社团信息一条
	 * @Param: [assosiation]
	 * @Return: boolean
	 * @Date: 2019/4/20 2:33
	 */
	public boolean insertAssosiation(String data) {
		Assosiation assosiation = Assosiation.parseJson(data);
		String sql = "insert into assosiations(name,information,chair,teacher,teacher_tel) values(?,?,?,?,?);";
		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, assosiation.getName());
			pst.setString(2, assosiation.getInformation());
			pst.setInt(3, assosiation.getChair());
			pst.setString(4, assosiation.getTeacher());
			pst.setString(5, assosiation.getTeacher_tel());
			pst.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description: 从数据库读取所有社团
	 * @Param: []
	 * @Return: java.sql.ResultSet
	 * @Date: 2019/4/20 3:00
	 */
	public ResultSet readAllAssosiations() throws SQLException {
		ResultSet res;
		String sql = "select * from assosiations;";
		Statement state = conn.createStatement();
		res = state.executeQuery(sql);
		return res;

	}

	public ResultSet readAssosiation(int id) throws SQLException {
		if(id ==0){
			return readAllAssosiations();
		}
		ResultSet res;
		String sql = "select * from assosiations where id = "+id+";";
		Statement state = conn.createStatement();
		res = state.executeQuery(sql);
		return res;
	}

	/**
	 * @Description: delete the specified assosiation
	 * @Param: [id]
	 * @Return: boolean
	 * @Date: 2019/4/20 3:16
	 */
	public boolean deleteAssosiation(int id) {
		String sql = "delete from assosiations where id = " + id + ";";
		Statement state = null;
		try {
			state = conn.createStatement();
			state.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Description: update assoisiation
	 * @Param: [assosiation]
	 * @Return: boolean
	 * @Date: 2019/4/20 3:17
	 */
	public boolean updateAssosiation(String data){
		Assosiation assosiation = Assosiation.parseJson(data);
		String sql = "replace into assosiations(id,name,information,chair,teacher,teacher_tel) values(?,?,?,?,?,?);";
		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, assosiation.getId());
			pst.setString(2, assosiation.getName());
			pst.setString(3, assosiation.getInformation());
			pst.setInt(4, assosiation.getChair());
			pst.setString(5, assosiation.getTeacher());
			pst.setString(6, assosiation.getTeacher_tel());
			pst.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	* @Description: insert member
	* @Param: [member]
	* @Return: boolean
	* @Date: 2019/4/21 20:59
	*/
	public boolean insertMember(String data){
		Member member = Member.parseJson(data);
		String sql = "insert into members(yiban_id,sex,college,sno,assosiation,grade,position) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,member.getYiban_id());
			pst.setInt(2,member.getSex());
			pst.setString(3,member.getCollege());
			pst.setInt(4,member.getSno());
			pst.setInt(5,member.getAssosiation());
			pst.setInt(6,member.getGrade());
			pst.setInt(7,member.getPosition());
			pst.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateMember(String data){
		Member member = Member.parseJson(data);
		String sql = "replace into members(id,yiban_id,sex,college,sno,assosiation,grade,position) values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,member.getId());
			pst.setInt(2,member.getYiban_id());
			pst.setInt(3,member.getSex());
			pst.setString(4,member.getCollege());
			pst.setInt(5,member.getSno());
			pst.setInt(6,member.getAssosiation());
			pst.setInt(7,member.getGrade());
			pst.setInt(8,member.getPosition());
			pst.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ResultSet readMember(int id){
		if(id == 0){
			return readAllMembers();
		}
		String sql = "select * from members where id ="+id+";";
		try {
			Statement state = conn.createStatement();
			ResultSet res = state.executeQuery(sql);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteMember(int id){
		String sql = "delete from members where id ="+id+";";
		try {
			Statement state = conn.createStatement();
			state.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ResultSet readAllMembers(){
		String sql = "select * from members";
		try {
			Statement state = conn.createStatement();
			ResultSet res = state.executeQuery(sql);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	@Autowired
	/**
	 * @Description: constructor
	 * @Param: [connectUtil]
	 * @Return:
	 * @Date:
	 */
	public DBManger(ConnectUtil connectUtil) {
		try {
			conn = connectUtil.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	}

	/**
	 * @Description: 测试获得pst中的sql语句
	 * @Param: []
	 * @Return: void
	 * @Date: 2019/4/20 5:31
	 */
	public void testPST() throws SQLException {
		PreparedStatement pst = conn.prepareStatement("insert * into assosiation(name,id) values(?,?)");
		pst.setString(1,"关爱智障协会");
		pst.setInt(2,999);
		System.out.println(pst.toString().split(": ")[1]);
	}

}
