package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Member;

public class OracleDao implements Dao {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public String jdbc_driver;
	public String jdbc_url;
	
	public OracleDao() {
		jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		jdbc_url = "jdbc:oracle:thin:@localhost:1521:xe";
	}
	
	public void con() {
		
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "hr", "hr");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void discon() {
		try {
			if (rs != null) {
				rs.close();
			}
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	@Override
	public void insert(Member m) {

		con();
		
		String sql = "insert into addr values(?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getTel());
			pstmt.setString(3, m.getAddress());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		discon();
	}

	@Override
	public ArrayList<Member> selectAll() {
		
		con();
		
		ArrayList<Member> list = new ArrayList<Member>();
		String sql = "select * from addr";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(new Member(rs.getString("name"), rs.getString(2), rs.getString(3)));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
		return list;
	}

	@Override
	public Member select(String name) {
		con();
		
		Member m = null;
		
		String sql = "select * from addr where name = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				m = new Member(rs.getString(1), rs.getString(2), rs.getString(3));
			}
//			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		discon();
		return m;
	}

	@Override
	public boolean update(Member m) {
		con();
		
		String sql = "update addr set tel = ?, address = ? where name = ?";
		int num = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(3, m.getName());
			pstmt.setString(1, m.getTel());
			pstmt.setString(2, m.getAddress());
			num = pstmt.executeUpdate(); // 한 행을 넣으면 1을 반환
//			pstmt.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		discon();
		
		if (num <= 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean delete(String name) {
		con();
		
		String sql = "delete addr where name = ?";
		int num = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			num = pstmt.executeUpdate();
						
		} catch(SQLException e) {
			e.printStackTrace();
		}
		discon();
		
		if (num <= 0 ) {
			return false;
		} else {
			return true;
		}
	}
}
