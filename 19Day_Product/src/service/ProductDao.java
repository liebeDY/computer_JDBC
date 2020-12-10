package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Product;

public class ProductDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public String jdbc_driver;
	public String jdbc_url;
	
	public ProductDao() {
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
	
	// 상품등록
	public void insert(Product p) {
		con();
		
		String sql = "insert into product values(?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p.getP_id());
			pstmt.setString(2, p.getName());
			pstmt.setInt(3, p.getNum());
			pstmt.setString(4, p.getCo());
			pstmt.setInt(5, p.getPrice());
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		discon();
	}
	
	// 제품 출력 - ArrayList
	public ArrayList printAll() {
		con();
		
		ArrayList<Product> list = new ArrayList<Product>();
		
		String sql = "select * from Product order by p_id";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5)));
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		discon();
		return list;
	}
	
	// 제품검색
	public Product selectProduct(int p_id) {
		con();
		Product p = null;
		
		String sql = "select * from Product where p_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				p = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5));
			}
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
		return p;
	}
	
	// 수정
	public void update(Product p) {
		con();
		
		String sql = "update Product set name = ?, num = ?, co = ?, price = ? where p_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getName());
			pstmt.setInt(2, p.getNum());
			pstmt.setString(3, p.getCo());
			pstmt.setInt(4, p.getPrice());
			pstmt.setInt(5, p.getP_id());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
	}

	// 삭제
	public void delete(int p_id) {
		con();
		
		String sql = "delete Product where p_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
	}
}
