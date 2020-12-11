package join;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoOracleImpl implements Dao {
	
	private DBConnect db = DBConnect.getInstance();
	private Connection conn = db.getConnection();
	private PreparedStatement pstmt;
	
	@Override
	public int insertMember(Member m) {
		String sql = "insert into member2 values(?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPwd());
			pstmt.setString(3, m.getName());
			pstmt.setString(4, m.getEmail());
			int line = pstmt.executeUpdate();
			pstmt.close();
			
			return line;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateMember(Member m) {
		String sql = "update member2 set pwd = ?, email = ? where id =?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getId());
			int line = pstmt.executeUpdate();
			pstmt.close();
			
			return line;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int removeMember(String id) {
		String sql = "delete member2 where id =?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			int line = pstmt.executeUpdate(); // 행을 반환
			pstmt.close();
			
			return line;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean login(String id, String pwd) {
		String sql = "select * from member2 where id = ? and pwd = ?";
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return true;
			}
			rs.close();
			pstmt.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Member memberInfo(String id) {
		String sql = "select * from member2 where id = ?";
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return new Member(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
			rs.close();
			pstmt.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
