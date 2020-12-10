package service;

import vo.AccountVO;

public interface Service {
	
	void insertAccount(AccountVO vo);
	AccountVO findAccount(String accNum);
	int depositMoney(String accNum, int money);
	int withDrawMoney(String accNum, int money);
	int sumAccount(String name);
	int avgAccoutn(String name);
	boolean findNameExist(String accountName);
	boolean findAccountExist(String accountNumber);
}
