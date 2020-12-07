package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DB {

	/*
	 *  create sequence Member_seq;

		create table Member (
		num number,
		name varchar2(50) primary key,
		addr varchar2(50) not null,
		tel varchar2(50) not null
		);
	 */
	
	
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
	public void MemberPrint() {
		con();
		
		String sql = "select * from Member";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println("��ȣ : " + rs.getInt(1));
				System.out.println("�̸� : " + rs.getString(2));
				System.out.println("�ּ� : " + rs.getString(3));
				System.out.println("��ȣ : " + rs.getString(4));
				System.out.println("=================");
			}
			discon();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// 3.�˻� : �̸����� �˻�
	public boolean MemberSearch(String name) {
		con();
		
		String sql = "select * from Member where name = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println("��ȣ : " + rs.getInt(1));
				System.out.println("�̸� : " + rs.getString(2));
				System.out.println("�ּ� : " + rs.getString(3));
				System.out.println("��ȣ : " + rs.getString(4));
				return true;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		discon();
		return false;
	}
	
	// 4.���� : �̸� �˻��� �ּ�, ��ȭ��ȣ ����
	public void update() {
		con();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("������ �̸��� �Է��ϼ��� : ");
		String name = sc.next();
		
		System.out.println("���ο� �ּҸ� �Է��ϼ��� : ");
		String addr = sc.next();
		
		System.out.println("���ο� ��ȣ�� �Է��ϼ��� : ");
		String tel = sc.next();
		
		String sql = "update Member set addr = ?, tel = ? where name = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, addr);
			pstmt.setString(2, tel);
			pstmt.setString(3, name);
			pstmt.executeUpdate();
			discon();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// 5.���� : ������ �̸� �˻�
	public void delete() {
		con();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("������ �̸��� �Է��ϼ��� : ");
		String name = sc.next();
		
		String sql = "delete from Member where name = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
			
			discon();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
