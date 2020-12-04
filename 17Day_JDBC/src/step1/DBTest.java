package step1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DBTest {

	public static void main(String[] args) {
		// 길
		Connection conn = null;
		// 자동차
		Statement stmt = null;
		
		// 오라클의 경로를 가져옴
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		// 오라클의 url / xe : 오라클 이름 / localhost:1521 - 오라클 포트
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:xe";

		
		// 먼저 connection을 열어줘야 한다
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url,"hr","hr");
			
			// connection 밑에 statement가 있다
			stmt = conn.createStatement();
			
			// oracle 쿼리문 작성
			String sql = "select last_name, job_id from employees";
			
			// 쿼리문 실행 결과 담는 변수
			ResultSet rs = stmt.executeQuery(sql);
			
			int i = 1;
			System.out.println("   name  	job");
			
			// rs.next() 하나의 열을 가져옴 -> 값이 없을 때까지 열 하나씩 검색
			while(rs.next()) {
				System.out.println(i + " : " + rs.getString(1) + " , " + rs.getString(2));
				i++;
			}
			// 열어준 순서 반대로 닫아주기
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}


