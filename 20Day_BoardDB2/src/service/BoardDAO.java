package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Board;

public class BoardDAO implements Dao {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public String jdbc_driver;
	public String jdbc_url;
	
	public BoardDAO() {
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
	public void insert(Board b) {
		con();
		
		String sql = "insert into board values(?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getId());
			pstmt.setString(2, b.getTitle());
			pstmt.setString(3, b.getContent());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		discon();
	}
	
	@Override
	public Board selectMember(String id) {
		con();
		
		Board b = null;
		
		String sql = "select * from board where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				b = new Board(rs.getString(1), rs.getString(2), rs.getString(3));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		discon();
		return b;
	}

	@Override
	public void update(Board m) {
		con();
		
		String sql = "update set contents = ?, title = ? where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getContent());
			pstmt.setString(2, m.getTitle());
			pstmt.setString(3, m.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
	}

	@Override
	public void delete(String id) {
		con();
		
		String sql = "delete board where id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
	}

	@Override
	public ArrayList<Board> selectAll() {
		con();
		
		ArrayList<Board> b = new ArrayList<Board>();
		
		String sql = "select * from board";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				b.add(new Board(rs.getString(1), rs.getString(2), rs.getString(3)));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		discon();
		return b;
				
	}

}
