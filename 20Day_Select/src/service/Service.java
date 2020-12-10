package service;

import java.util.ArrayList;

import vo.Member;

public interface Service {
	
	void addMember(Member m);
	
	Member getMember(String id);
	
	void editMember(Member m);
	
	void delMember(String id);
	
	ArrayList<Member> getMembers(int type);
}
