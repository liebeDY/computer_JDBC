package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Product;

public class OracleDao implements Dao{
	
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
	public void insert(Product p) {
		con();
		
		String sql = "insert into product(num, name, price) values(product_seq.nextval, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getName());
			pstmt.setInt(2, p.getPrice());
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		discon();
	}

	@Override
	public Product select(int num) {
		con();
		Product p = null;
		
		String sql = "select * from product where num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				p = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
		return p;
	}

	@Override
	public ArrayList<Product> selectAll() {
		con();
		
		System.out.println("오라클 전체출력");
		
		ArrayList<Product> v = new ArrayList<Product>();
		String sql = "select * from product";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				v.add(new Product(rs.getInt(1), rs.getString(2), rs.getInt(3)));
			}
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		discon();
		
		return v;
	}

	@Override
	public void delete(int num) {
		con();
		
		String sql = "delete product where num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			num = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		discon();
	}

	@Override
	public void update(int num, Product p) {
		con();
		
		String sql = "update product set name = ?, price = ? where num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getName());
			pstmt.setInt(2, p.getPrice());
			pstmt.setInt(3, p.getNum());
			num = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
	}

}
