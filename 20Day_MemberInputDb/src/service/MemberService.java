package service;

import java.util.ArrayList;

import vo.MemberVO;

public class MemberService {
	
	private Dao dao;

	// ��ĳ���� : ��ü�� �ڽ��� ����(�������̽��� ȥ�� ��ü ���� x), ������� ��ü�� �θ� �����´� -> �ڽ��� ���Ƶ� �θ� �� �����ü� �ִ�
	// Ŭ������ �þ�� dao�� �� �����ü� ����
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
