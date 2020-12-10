package service;

import java.util.ArrayList;

import vo.Member;

public class MemberService implements Service {

	private Dao dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}

	@Override
	public void addMember(Member m) {
		dao.insert(m);
	}

	@Override
	public Member getMember(String id) {
		return dao.selectMember(id);
	}

	@Override
	public void editMember(Member m) {
		dao.update(m);
	}

	@Override
	public void delMember(String id) {
		dao.delete(id);
	}

	@Override
	public ArrayList<Member> getMembers(int type) {
		return dao.selectAll(type);
	}
}
