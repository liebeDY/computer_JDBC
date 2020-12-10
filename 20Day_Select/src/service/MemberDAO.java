package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Employee;
import vo.Member;
import vo.Professor;
import vo.Student;

public class MemberDAO implements Dao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public String jdbc_driver;
	public String jdbc_url;
	
	public MemberDAO() {
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

	
	@Override
	public void insert(Member m) {
		con();
		
		String sql = "insert into school values(?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getName());
			pstmt.setString(3, m.getTel());
			pstmt.setString(4, m.getAddr());
			pstmt.setInt(5, m.getType());
			
			if (m instanceof Professor) {
				pstmt.setString(6, ((Professor) m).getDept()); 
			} else if (m instanceof Student) {
				pstmt.setString(6, ((Student) m).getSchool());
			} else if (m instanceof Employee) {
				pstmt.setString(6, ((Employee)m).getJob());
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
	}
	

	@Override
	public Member selectMember(String id) {
		con();
		
		String sql = "select * from member where id = ?";
		
		Member m = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getInt("type") == 1) {
					m = new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
							rs.getInt(5), rs.getString(6));
				} else if (rs.getInt("type") == 2) {
					m = new Professor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
							rs.getInt(5), rs.getString(6));
				} else if (rs.getInt("type") == 3) {
					m = new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
							rs.getInt(5), rs.getString(6));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		discon();
		return m;
	}
	

	@Override
	public void update(Member m) {
		con();
		
		String sql = "update school set tel = ?, addr = ?, etc = ? where id = ?";
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getTel());
			pstmt.setString(2, m.getAddr());
			
			if (m.getType() == 1) {
				pstmt.setString(3, ((Student) m).getSchool());
			} else if (m.getType() == 2) {
				pstmt.setString(3, ((Professor) m).getDept());
			} else if (m.getType() == 3) {
				pstmt.setString(3, ((Employee) m).getJob());
			}
			
			pstmt.setString(4, m.getId());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
	}

	@Override
	public void delete(String id) {
		con();
		
		String sql = "delete school where id =?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		discon();
	}

	@Override
	public ArrayList<Member> selectAll(int type) {
		con();
		// return으로 ArrayList 이니까 먼저 m 을 만들어둔다
		ArrayList<Member> m = new ArrayList<Member>();
		
		String sql = "select * from school where type = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			rs = pstmt.executeQuery();

			if (type == 1) {
				while (rs.next()) {
					m.add(new Student(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6)));
				}
			} else if (type == 2) {
				while (rs.next()) {
					m.add(new Professor(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6)));
				}
			} else if (type == 3) {
				while (rs.next()) {
					m.add(new Employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
		return m;
	}
}
