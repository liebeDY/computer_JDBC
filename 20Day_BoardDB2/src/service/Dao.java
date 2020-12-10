package service;

import java.util.ArrayList;

import vo.Board;

public interface Dao {

	void con();
	
	void discon();
	
	void insert(Board b);
	
	Board selectMember(String id);
	
	void update(Board m);
	
	void delete(String id);
	
	ArrayList<Board> selectAll();
}
