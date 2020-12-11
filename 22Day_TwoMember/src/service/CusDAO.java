package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Customer;

public class CusDAO implements Dao2 {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public String jdbc_driver;
	public String jdbc_url;
	
	public CusDAO() {
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
	public void Cusinsert(Customer b) {
		con();
		
		String sql = "insert into Customer values(?,?,?)";
				
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getName());
			pstmt.setString(2, b.getAddr());
			pstmt.setString(3, b.getTel());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
	}

	@Override
	public Customer Cusselect(String name) {
		con();
		
		String sql = "select * from Customer where name=?";
		
		Customer c = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				c = new Customer(rs.getString(1), rs.getString(2), rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
		return c;
	}

	@Override
	public void Cusupdate(Customer b) {
		con();
		
		String sql = "update Customer set addr=?, tel=? where name=?";
				
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getAddr());
			pstmt.setString(2, b.getTel());
			pstmt.setString(3, b.getName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
	}

	@Override
	public void Cusdelete(String name) {
		con();
		
		String sql = "delete Customer where name=?";
				
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
	}

	@Override
	public ArrayList<Customer> CusselectAll() {
		con();
		
		String sql = "select * from Customer ";
			
		ArrayList<Customer> v = new ArrayList<Customer>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				v.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
		return v;
	}

}
