package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vo.MemberVO;

public class MemberDAO implements Dao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public String jdbc_driver;
	public String jdbc_url;
	
	public MemberDAO() {
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

	// µî·Ï
	@Override
	public void insert(MemberVO m) {
		con();
	
		String sql = "insert into member values(?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getName());
			pstmt.setString(3, m.getTel());
			pstmt.setString(4, m.getAddr());
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		discon();
	}

	@Override
	public MemberVO selectMember(String id) {
		con();
		MemberVO m = null;
		String sql = "select * from member where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				m = new MemberVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		discon();
		return m;
	}

	@Override
	public void update(MemberVO m) {
		con();
		
		String sql = "update member set tel = ?, addr = ? where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getTel());
			pstmt.setString(2, m.getAddr());
			pstmt.setString(3, m.getId());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		discon();
	}

	@Override
	public void delete(String id) {
		con();
		
		String sql = "delete member where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		discon();
	}

	@Override
	public ArrayList<MemberVO> selectAll() {
		con();
		
		ArrayList<MemberVO> v = new ArrayList<MemberVO>();
		
		String sql = "select * from member";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				v.add(new MemberVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		discon();
		return v;
	}

}
