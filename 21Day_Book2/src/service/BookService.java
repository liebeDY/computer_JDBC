package service;

import vo.BookVO;

public class BookService {
  
	private Dao dao;
	
	public BookService() {
		dao = new BookDAO();
	}
	  
	public void insert(BookVO b) {
		dao.bookinsert(b);
	}
	
	public void printAll() {
		System.out.println(dao.bookselectAll());
	}
	
	public BookVO findbook(String id) {
		return dao.bookselect(id);
	}
	
	public void delete(String id) {
		dao.bookdelete(id);
	}
	
	public void update(BookVO b) {
		dao.bookupdate(b);
	}
}
