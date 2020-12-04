package step5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC4 {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "hr", "hr");
			System.out.println("jdbc driver loading ok");
			stmt = conn.createStatement();
			
			String sql = "delete from member where name = '±è¿¬¾Æ'";
			int result = stmt.executeUpdate(sql);
			System.out.println(result);
			
			stmt.close();
			conn.close();
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
	}
}
