package step2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC1 {

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		// 오라클의 url / xe : 오라클 이름 / localhost:1521 - 오라클 포트
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "hr", "hr");
			stmt = conn.createStatement();
			String sql = "select id, password, name, address from member";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
