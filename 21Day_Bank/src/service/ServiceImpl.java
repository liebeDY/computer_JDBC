package service;

import vo.AccountVO;

public class ServiceImpl implements Service {
	
	private Dao dao;
	
	public ServiceImpl() {
		dao = new AccountDao();
	}
	@Override
	public void insertAccount(AccountVO vo) {
		dao.insertAccount(vo);
	}

	@Override
	public AccountVO findAccount(String accNum) {
		return dao.findAccount(accNum);
	}

	@Override
	public int depositMoney(String accNum, int money) {
		return dao.depositMoney(accNum, money);
	}

	@Override
	public int withDrawMoney(String accNum, int money) {
		return dao.withDrawMoney(accNum, money);
	}

	@Override
	public int sumAccount(String name) {
		return dao.sumAccount(name);
	}

	@Override
	public int avgAccoutn(String name) {
		return dao.avgAccount(name);
	}

	@Override
	public boolean findNameExist(String accountName) {
		return dao.findNameExist(accountName);
	}

	@Override
	public boolean findAccountExist(String accountNumber) {
		return dao.findAccountExist(accountNumber);
	}
}
