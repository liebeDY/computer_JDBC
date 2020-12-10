package service;

import java.lang.reflect.Member;
import java.util.ArrayList;

import vo.MemberVO;

public interface Dao {
	
	// 메서드 7개
	void con();
	
	void discon();
	
	void insert(MemberVO m);
	
	MemberVO selectMember(String id);
	
	void update(MemberVO m);
	
	void delete(String id);
	
	ArrayList<MemberVO> selectAll();
}
