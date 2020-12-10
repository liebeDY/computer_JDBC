package service;

import java.util.ArrayList;

import vo.Member;

public interface Dao {

	void con();
	
	void discon();
	
	void insert(Member m);
	
	Member selectMember(String id);
	
	void update(Member m);
	
	void delete(String id);
	
	ArrayList<Member> selectAll(int type);
}
