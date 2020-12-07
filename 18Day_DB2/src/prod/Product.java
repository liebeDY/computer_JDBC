package prod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Product {

	public Connection conn = null;
	public PreparedStatement pstmt = null;
	public String jdbc_driver;
	public String jdbc_url;
	public ResultSet rs = null;
	
	private int p_id;
	private String name;
	private int num;
	private String co;
	private int price;
	
	public Product() {
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
	
	// 연결 끊기
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
	
	// 제품 생산
	public void ProductInsert() {
		
		con();
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println("제품 번호를 입력하세요 :");
		p_id = sc.nextInt();
		System.out.println("제품 이름을 입력하세요 : ");
		name = sc.next();
		System.out.println("제품 수량을 입력하세요 : ");
		num = sc.nextInt();
		System.out.println("제조사를 입력하세요 : ");
		co = sc.next();
		System.out.println("제품 가격을 입력하세요 : ");
		price = sc.nextInt();
		
		String sql = "insert into product values(?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_id);
			pstmt.setString(2, name);
			pstmt.setInt(3, num);
			pstmt.setString(4, co);
			pstmt.setInt(5, price);
			pstmt.executeUpdate();
			discon();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	// 제품 출력
	public void ProductPrint() {
		con();
		
		String sql = "select * from product";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println("제품번호: " + rs.getInt(1));
				System.out.println("제품이름: " + rs.getString(2));
				System.out.println("제품수량: " + rs.getInt(3));
				System.out.println("제조사: " + rs.getString(4)); 
				System.out.println("제품가격: " + rs.getInt(5));
				System.out.println("=====================");
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		discon();
	}
	
	// 제품 검색
	public boolean Productsearch(int p_id) {
		con();
		
		String sql = "select * from product where p_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				if (p_id == rs.getInt(1)) {
					System.out.println("제품번호: " + rs.getInt(1));
					System.out.println("제품이름: " + rs.getString(2));
					System.out.println("제품수량: " + rs.getInt(3));
					System.out.println("제조사: " + rs.getString(4)); 
					System.out.println("제품가격: " + rs.getInt(5));
					System.out.println("=====================");
					return true;
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		discon();
		return false;
	}
	
	// 주문
	public void order() {
		Scanner sc = new Scanner(System.in);
		
		// 입력받기
//		System.out.println("제조사 : ");
//		co = sc.next();
		System.out.println("상호명 :");
		String cus = sc.next();
		System.out.println("제품번호 : ");
		p_id = sc.nextInt();
		System.out.println("제품 수량 : ");
		num = sc.nextInt();
		
		// 수량 업데이트 적용
		boolean f = Productsearch(p_id);
		
		if (!f) {
			System.out.println("제품 코드 오류");
			return;
		}
		con();
		
		try {
			String sql1 = "select num from product where p_id = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, p_id);
			rs = pstmt.executeQuery();
			rs.next();
			
			int n = rs.getInt(1);
			System.out.println(n);
			if (n < num) {
				System.out.println("수량이 큽니다.");
				return;
				
			} else {
				n -= num;
				NumUpdate(p_id, n);
			}
			
		} catch(Exception e) {
			System.out.println(e);
		}
		discon();
		// order DB에 넣기
		con();
		
		try {
			String sql = "insert into order1 values(?, SYSDATE, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cus);
			pstmt.setInt(3, p_id);
			pstmt.setInt(4, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		discon();
	}
	
	// 수량을 업데이트(수정) 해주는 메서드
	public void NumUpdate(int p_id, int num) {
		con();
		
		try {
			String sql = "update product set num = ? where p_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, p_id);
			pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		}
		discon();
	}
	
	
	public void print_order() {
		con();
		String sql = "select * from order1";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println("상호명 : " + rs.getString(1));
				System.out.println("주문날짜 : " + rs.getString(2));
				System.out.println("제품번호 : " + rs.getInt(3));
				System.out.println("주문수량 : " + rs.getInt(4));
				System.out.println("=========================");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
