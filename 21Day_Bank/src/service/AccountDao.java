package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.InsufficientMoneyException;
import exception.InvalidBalanceException;
import exception.NotFoundAccountException;
import vo.AccountVO;

public class AccountDao implements Dao {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public String jdbc_driver;
	public String jdbc_url;
	
	public AccountDao() {
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
	public void insertAccount(AccountVO vo) {
		con();
		
		String sql = "insert into bankvo(AccountNumber, AccountName, balance) values(?,?,?)";
		
		try {
			
			if (vo.getBalance() <= 0) {
				throw new InsufficientMoneyException("입금액이 0입니다.");
			} 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getAccountNumber());
			pstmt.setString(2, vo.getAccountName());
			pstmt.setInt(3, vo.getBalance());
			int result = pstmt.executeUpdate();
			System.out.println("계좌 생성 : " + result);
		
		// catch문을 2개로 한 이유 : 세부적으로 하나씩 예외 잡고싶을 때
		// -> catch 1개로 Exception 써도 된다
		} catch (InsufficientMoneyException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
	}

	@Override
	public int depositMoney(String accNum, int money) {
		con();
		String sql = "update bankvo set balance = (balance + ?) where accountNumber = ?";
		
		try {
		
			if (!findAccountExist(accNum)) {
				throw new NotFoundAccountException("존재하지 않는 계좌입니다.");
			}
			
			if (money <= 0) {
				throw new InsufficientMoneyException("입금액이 0원 이하입니다.");
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setString(2, accNum);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NotFoundAccountException e) {
			e.printStackTrace();
		} catch (InsufficientMoneyException e) {
			e.printStackTrace();
		}
		discon();
		return money;
	}

	@Override
	public int withDrawMoney(String accNum, int money) {
		con();
		
		String sql = "update bankvo set balance = (balance - ?) where accountNumber = ? and balance >= ?";
		
		if (!findAccountExist(accNum)) {
			try {
				throw new NotFoundAccountException("존재하지 않는 계좌입니다.");
			} catch (NotFoundAccountException e) {
				e.printStackTrace();
			}
		}
		
		if (money <= 0) {
			try {
				throw new InsufficientMoneyException("출금액이 0원 이하입니다.");
			} catch (InsufficientMoneyException e) {
				e.printStackTrace();
			}
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, money);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			pstmt.setString(2, accNum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			pstmt.setInt(3, money);
		} catch (SQLException e) {
			e.printStackTrace();;
		}
			
		int result = 0;
		
		try {
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (result == 0) {
			try {
				throw new InvalidBalanceException("잔액이 부족합니다.");
			} catch (InvalidBalanceException e) {
				e.printStackTrace();
			}
		}
		discon();
		return money;
	}

	@Override
	public int sumAccount(String name) {
		con();
		
		String sql = "select sum(balance) from bankvo where accountName = ?";
		
		if (!findNameExist(name)) {
			try {
				throw new NotFoundAccountException("찾는 사람이 없습니다.");
			} catch(NotFoundAccountException e) {
				e.printStackTrace();
			}
		}
		int sum = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		// try catch 를 여러구문으로 쓰는 이유
		// Exception 으로만 쓰면 Exception 다 가져온다 = 메모리 낭비 = 더 빠르다
		try {
			rs = pstmt.executeQuery();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
			rs.next();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
			sum = rs.getInt(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		discon();
		return sum;
	}

	@Override
	public int avgAccount(String name) {
		con();
		
		String sql = "select avg(balance) from bankvo where accountName = ?";
		
		if (!findNameExist(name)) {
			try {
				throw new NotFoundAccountException("찾는 사람이 없습니다.");
			} catch(NotFoundAccountException e) {
				e.printStackTrace();
			}
		}
		int avg = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		// try catch 를 여러구문으로 쓰는 이유
		// Exception 으로만 쓰면 Exception 다 가져온다 = 메모리 낭비 = 더 빠르다
		try {
			rs = pstmt.executeQuery();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
			rs.next();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
			avg = rs.getInt(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		discon();
		return avg;
	}

	@Override
	public boolean findNameExist(String accountName) {
		boolean flag = true;
		String sql = "select accountName from bankvo where accountName = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accountName);
		} catch (SQLException e) {
			e.printStackTrace();;
		}
		
		try {
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			flag = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean findAccountExist(String accountNumber) {
		// findAccount 메서드에서 쓸 거라 con 이 없어도 된다
		
		boolean flag = true;
		String sql = "select accountNumber from bankvo where accountNumber = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accountNumber);
		} catch (SQLException e) {
			e.printStackTrace();;
		}
		
		try {
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			flag = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public AccountVO findAccount(String accNum) {
		con();
		
		String sql = "select * from bankvo where accountNumber = ?";
		
		AccountVO a = null;
		
		try {
			
			if (!findAccountExist(accNum)) {
				throw new NotFoundAccountException("존재하지 않는 계좌입니다.");
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accNum);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				a = new AccountVO(rs.getString(1), rs.getString(2), rs.getInt(3));
			}
			
		} catch (NotFoundAccountException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		discon();
		return a;
	}
}
