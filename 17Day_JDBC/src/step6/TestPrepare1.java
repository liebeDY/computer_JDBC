package step6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestPrepare1 {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "hr", "hr");
			System.out.println("jdbc driver loading ok");
			
			// prepared Statement
			String sql = "insert into member(id, password, name, address) values(?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "ibatis");
			pstmt.setString(2, "bcad");
			pstmt.setString(3, "이진우");
			pstmt.setString(4, "신흥1동");
			
			int result = pstmt.executeUpdate();
			System.out.println("insert : " + result);
			
			pstmt.close();
			conn.close();
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}
