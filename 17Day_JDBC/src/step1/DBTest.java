package step1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DBTest {

	public static void main(String[] args) {
		// ��
		Connection conn = null;
		// �ڵ���
		Statement stmt = null;
		
		// ����Ŭ�� ��θ� ������
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		// ����Ŭ�� url / xe : ����Ŭ �̸� / localhost:1521 - ����Ŭ ��Ʈ
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:xe";

		
		// ���� connection�� ������� �Ѵ�
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url,"hr","hr");
			
			// connection �ؿ� statement�� �ִ�
			stmt = conn.createStatement();
			
			// oracle ������ �ۼ�
			String sql = "select last_name, job_id from employees";
			
			// ������ ���� ��� ��� ����
			ResultSet rs = stmt.executeQuery(sql);
			
			int i = 1;
			System.out.println("   name  	job");
			
			// rs.next() �ϳ��� ���� ������ -> ���� ���� ������ �� �ϳ��� �˻�
			while(rs.next()) {
				System.out.println(i + " : " + rs.getString(1) + " , " + rs.getString(2));
				i++;
			}
			// ������ ���� �ݴ�� �ݾ��ֱ�
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}


