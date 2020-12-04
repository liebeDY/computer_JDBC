package step3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC2 {

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "hr", "hr");
			stmt = conn.createStatement();
			
			// spring, aaaa, ��ŷ�, �浿 �߰��ϱ�
			String sql = "insert into member(id, password, name, address) values ('spring', 'aaaa', '�迬��', '�Ⱦ�')";
			
			// row ����
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
