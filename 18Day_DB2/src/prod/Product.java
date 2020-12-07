package prod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Product {

	public Connection conn = null;
	public PreparedStatement pstmt = null;
	public String jdbc_driver;
	public String jdbc_url;
	public ResultSet rs = null;
	
	private int p_id;
	private String name;
	private int num;
	private String co;
	private int price;
	
	public Product() {
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
	
	// ��ǰ ����
	public void ProductInsert() {
		
		con();
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println("��ǰ ��ȣ�� �Է��ϼ��� :");
		p_id = sc.nextInt();
		System.out.println("��ǰ �̸��� �Է��ϼ��� : ");
		name = sc.next();
		System.out.println("��ǰ ������ �Է��ϼ��� : ");
		num = sc.nextInt();
		System.out.println("�����縦 �Է��ϼ��� : ");
		co = sc.next();
		System.out.println("��ǰ ������ �Է��ϼ��� : ");
		price = sc.nextInt();
		
		String sql = "insert into product values(?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_id);
			pstmt.setString(2, name);
			pstmt.setInt(3, num);
			pstmt.setString(4, co);
			pstmt.setInt(5, price);
			pstmt.executeUpdate();
			discon();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	// ��ǰ ���
	public void ProductPrint() {
		con();
		
		String sql = "select * from product";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println("��ǰ��ȣ: " + rs.getInt(1));
				System.out.println("��ǰ�̸�: " + rs.getString(2));
				System.out.println("��ǰ����: " + rs.getInt(3));
				System.out.println("������: " + rs.getString(4)); 
				System.out.println("��ǰ����: " + rs.getInt(5));
				System.out.println("=====================");
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		discon();
	}
	
	// ��ǰ �˻�
	public boolean Productsearch(int p_id) {
		con();
		
		String sql = "select * from product where p_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				if (p_id == rs.getInt(1)) {
					System.out.println("��ǰ��ȣ: " + rs.getInt(1));
					System.out.println("��ǰ�̸�: " + rs.getString(2));
					System.out.println("��ǰ����: " + rs.getInt(3));
					System.out.println("������: " + rs.getString(4)); 
					System.out.println("��ǰ����: " + rs.getInt(5));
					System.out.println("=====================");
					return true;
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		discon();
		return false;
	}
	
	// �ֹ�
	public void order() {
		Scanner sc = new Scanner(System.in);
		
		// �Է¹ޱ�
//		System.out.println("������ : ");
//		co = sc.next();
		System.out.println("��ȣ�� :");
		String cus = sc.next();
		System.out.println("��ǰ��ȣ : ");
		p_id = sc.nextInt();
		System.out.println("��ǰ ���� : ");
		num = sc.nextInt();
		
		// ���� ������Ʈ ����
		boolean f = Productsearch(p_id);
		
		if (!f) {
			System.out.println("��ǰ �ڵ� ����");
			return;
		}
		con();
		
		try {
			String sql1 = "select num from product where p_id = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, p_id);
			rs = pstmt.executeQuery();
			rs.next();
			
			int n = rs.getInt(1);
			System.out.println(n);
			if (n < num) {
				System.out.println("������ Ů�ϴ�.");
				return;
				
			} else {
				n -= num;
				NumUpdate(p_id, n);
			}
			
		} catch(Exception e) {
			System.out.println(e);
		}
		discon();
		// order DB�� �ֱ�
		con();
		
		try {
			String sql = "insert into order1 values(?, SYSDATE, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cus);
			pstmt.setInt(3, p_id);
			pstmt.setInt(4, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		discon();
	}
	
	// ������ ������Ʈ(����) ���ִ� �޼���
	public void NumUpdate(int p_id, int num) {
		con();
		
		try {
			String sql = "update product set num = ? where p_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, p_id);
			pstmt.executeUpdate();
		} catch(Exception e) {
			System.out.println(e);
		}
		discon();
	}
	
	
	public void print_order() {
		con();
		String sql = "select * from order1";
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println("��ȣ�� : " + rs.getString(1));
				System.out.println("�ֹ���¥ : " + rs.getString(2));
				System.out.println("��ǰ��ȣ : " + rs.getInt(3));
				System.out.println("�ֹ����� : " + rs.getInt(4));
				System.out.println("=========================");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
