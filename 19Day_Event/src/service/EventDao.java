package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import vo.Event;

public class EventDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public String jdbc_driver;
	public String jdbc_url;
	
	public EventDao() {
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
	
	public void enroll() {
		con();
		Scanner sc = new Scanner(System.in);
		System.out.println("¿Ã∏ﬁ¿œ : ");
		String email = sc.next();
		
		String sql = "insert into A values(A_seq.nextval, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.executeUpdate();
			
			discon();
			 
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public ArrayList print() {
		con();
		
		ArrayList<Event> v = new ArrayList<Event>();
		String sql = "select * from A";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				v.add(new Event(rs.getInt(1), rs.getString(2)));
			}
			rs.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		discon();
		return v;
	}
	
	public Event search(int num) {
		con();
		Event m = null;
		
		String sql = "select * from A where num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				m = new Event(rs.getInt(1), rs.getString(2));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		discon();
		return m;
	}
	
	public void updateEvent(Event p) {
		con();
		
		String sql = "update A set emil = ? where num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getEmail());
			pstmt.setInt(2, p.getNum());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
	}
	
	public void delete(int num) {
		con();
		
		String sql = "delete A where num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
	}
}
