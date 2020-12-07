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
	
	// ���� ����
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
	
	// 1.��� : �̸�, �ּ�, ��ȭ��ȣ
	public void MemberInsert() {
		con();
		Scanner sc = new Scanner(System.in);
		System.out.println("�̸��� �Է��ϼ��� : ");
		String name = sc.next();
		
		System.out.println("�ּҸ� �Է��ϼ��� : ");
		String addr = sc.next();
		
		System.out.println("��ȭ��ȣ�� �Է��ϼ��� : ");
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
	
	// 2.��üȸ�� : ��üȸ�� ��� ��ȣ, �̸�, �ּ�
	
	
	// 3.�˻� : �̸����� �˻�
	
	
	// 4.���� : �̸� �˻��� �ּ�, ��ȭ��ȣ ����
	
	
	// 5.���� : ������ �̸� �˻�
	
	
	
	// 6.����
}
