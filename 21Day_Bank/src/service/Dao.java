package service;

import vo.AccountVO;

public interface Dao {
	
	// 신규계좌, 계좌검색, 입금, 출금, 합계, 평균
	void con();
	void discon();
	void insertAccount(AccountVO vo);
	AccountVO findAccount(String accNum);
	int depositMoney(String accNum, int money);
	int withDrawMoney(String accNum, int money);
	int sumAccount(String name);
	int avgAccount(String name);
	boolean findNameExist(String accountName);
	boolean findAccountExist(String accountNumber);
}
