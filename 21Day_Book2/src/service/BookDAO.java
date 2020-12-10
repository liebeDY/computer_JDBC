package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exception.BookNotFoundException;
import vo.BookVO;

public class BookDAO implements Dao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public String jdbc_driver;
	public String jdbc_url;
	
	public BookDAO() {
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
	public void bookinsert(BookVO b) {
		con();
		
		String sql = "insert into book values(?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getId());
			pstmt.setString(2, b.getAuthor());
			pstmt.setString(3, b.getTitle());
			pstmt.setString(4, b.getPublisher());
			pstmt.setInt(5, b.getPrice());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		discon();
	}

	@Override
	public BookVO bookselect(String id) {
		con();
		
		String sql = "select * from book where id = ?";
		
		BookVO b = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				b = new BookVO(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
			} else {
				throw new BookNotFoundException("해당하는 책이 없음");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		discon();
		return b;
	}

	@Override
	public void bookupdate(BookVO b) {
		con();
		
		String sql = "update book set tilte = ?, author = ?, publisher = ?, price = ? where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getAuthor());
			pstmt.setString(3, b.getPublisher());
			pstmt.setInt(4, b.getPrice());
			pstmt.setString(5, b.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
	}

	@Override
	public void bookdelete(String id) {
		int n = 0; // excuteUpdate가 됐을 때 일치하는 행을 확인하기 위해
		
		con();
		
		String sql = "delete book where id = ?";
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			// executeUpdate() = 행 수를 반환
			n = pstmt.executeUpdate();
			
			// 0 == 삭제한 행이 없다
			if (n == 0) {
				throw new BookNotFoundException("해당하는 책이 없습니다.");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		discon();
	}

	@Override
	public ArrayList<BookVO> bookselectAll() {
		con();
		
		ArrayList<BookVO> m = new ArrayList<BookVO>();
		
		String sql = "select * from book";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				m.add(new BookVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		discon();
		return m;
	}
}
