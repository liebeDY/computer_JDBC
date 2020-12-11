package service;

import java.util.ArrayList;

import vo.BookVO;

public interface Dao {

	void con();
	
	void discon();
	
	void bookinsert(BookVO b);
	
	BookVO bookselect(String id);
	
	void bookupdate(BookVO b);
	
	void bookdelete(String id);
	
	ArrayList<BookVO> bookselectAll();
}
