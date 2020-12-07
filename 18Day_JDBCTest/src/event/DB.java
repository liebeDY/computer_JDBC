package event;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DB {

	public Connection conn = null;
	public PreparedStatement pstmt = null;
	public String jdbc_driver;
	public String jdbc_url;
	public ResultSet rs = null;
	
	public DB() {
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
	
	public void enroll() {
		// 연결해서 사용해야 되니까 con을 먼저 호출
		con();
		Scanner sc = new Scanner(System.in);
		System.out.println("이메일 : ");
		String email = sc.next();
		String sql = "insert into A values(A_seq.nextval, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	// 출력 = select 쿼리문
	public void print() {
		
		con();
		String sql = "select * from A";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2));
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
