package service;

import java.util.ArrayList;

import vo.MemberVO;

public class MemberService {
	
	private Dao dao;

	// 업캐스팅 : 객체는 자식이 생성(인터페이스는 혼자 객체 생성 x), 만들어진 객체를 부모가 가져온다 -> 자식이 많아도 부모가 다 가져올수 있다
	// 클래스가 늘어나도 dao로 다 가져올수 있음
	public MemberService() {
		dao = new MemberDAO();
	}
	
	public void addMemberVO(MemberVO memberVO) {
		dao.insert(memberVO);
	}
	
	public MemberVO findMemberVO(String id) {
		return dao.selectMember(id);
	}
	
	public void updateMemberVO(MemberVO memberVO) {
		dao.update(memberVO);
	}
	
	public void deleteMemberVO(String id) {
		dao.delete(id);
	}
	
	public ArrayList<MemberVO> printAll() {
		return dao.selectAll();
	}
	
//	public void printAll() {
//		System.out.println(dao.selectAll());
//	}
}
