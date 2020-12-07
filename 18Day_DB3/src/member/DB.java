package member;

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
	
	// 1.등록 : 이름, 주소, 전화번호
	public void MemberInsert() {
		con();
		Scanner sc = new Scanner(System.in);
		System.out.println("이름을 입력하세요 : ");
		String name = sc.next();
		
		System.out.println("주소를 입력하세요 : ");
		String addr = sc.next();
		
		System.out.println("전화번호를 입력하세요 : ");
		String tel = sc.next();
		
		String sql = "insert into Member values(Member_seq.nextval, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, addr);
			pstmt.setString(3, tel);
			pstmt.executeUpdate();
			discon();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// 2.전체회원 : 전체회원 출력 번호, 이름, 주소
	
	
	// 3.검색 : 이름으로 검색
	
	
	// 4.수정 : 이름 검색후 주소, 전화번호 수정
	
	
	// 5.삭제 : 삭제할 이름 검색
	
	
	
	// 6.종료
}
