package service;

import java.util.ArrayList;

import vo.Member;

public class ServiceImpl implements Service{

	private Dao dao;
	
	public ServiceImpl() {
		dao = new OracleDao();
	} 
	
	@Override
	public void addMember(Member m) {
		dao.insert(m);
	} 

	@Override
	public ArrayList<Member> getMembers() {

		return null;
	}

	@Override
	public Member getMember(String name) {
		return dao.select(name);
	}

	@Override
	public boolean editMember(Member m) {
		return dao.update(m);
	}

	@Override
	public boolean delMember(String name) {
		return dao.delete(name);
	}
}
