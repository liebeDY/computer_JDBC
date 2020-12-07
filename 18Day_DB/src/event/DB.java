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
	
	public void enroll() {
		// �����ؼ� ����ؾ� �Ǵϱ� con�� ���� ȣ��
		con();
		Scanner sc = new Scanner(System.in);
		System.out.println("�̸��� : ");
		String email = sc.next();
		String sql = "insert into A values(A_seq.nextval, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.executeUpdate();
			discon();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void print() {
		
		con();
		String sql = "select * from A";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2));
			}
			discon();
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public boolean search(int num) {
		con();
		String sql = "select * from A where num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			// sql���� ?�� �ִ°�
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getString(2));
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		discon();
		return false;
		
	}
	
	public void update() {
		con();
		Scanner sc = new Scanner(System.in);
		System.out.println("������ ����� ��ȣ�� �Է��ϼ��� :");
		int j = sc.nextInt();
		
		System.out.println("������ ����� �̸����� �Է��ϼ��� : ");
		String email = sc.next();
		
		String sql = "update A set email = ? where num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setInt(2, j);
			pstmt.executeUpdate();
			
			discon();
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	public void delete() {
		con();
		Scanner sc = new Scanner(System.in);
		System.out.println("������ ����� ��ȣ�� �Է��ϼ��� :");
		int j = sc.nextInt();
		
		String sql = "delete from A where num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, j);
			pstmt.executeQuery();
			
			discon();

		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
}
